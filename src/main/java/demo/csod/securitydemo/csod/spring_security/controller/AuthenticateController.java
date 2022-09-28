package demo.csod.securitydemo.csod.spring_security.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import demo.csod.securitydemo.csod.spring_security.dto.LoginRequestDTO;
import demo.csod.securitydemo.csod.spring_security.dto.RegisterDto;
import demo.csod.securitydemo.csod.spring_security.service.AuthenticateService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Iterator;

@Controller
@Slf4j
public class AuthenticateController {

    public static final String REGISTER = "/register";
    @Autowired
    AuthenticateService authenticateService;

    @Autowired
    RestTemplate restTemplate;

    private final String REGISTERED_SUCCESS = "User Registered Successfully";
    private final String LOGIN_SUCCESS = "User Logged in";
    private final String GRAPHQL_QUERY = "{ users (where: {id_eq: 1}){firstName email creationDate}}";
    //    private final String GRAPHQL_QUERY = "{\n" +
//        "  users(where: {inactive_eq: false}) {\n" +
//        "    firstName\n" +
//        "    email\n" +
//        "    creationDate\n" +
//        "  }\n" +
//        "}";
    private final String REST_API_URL = "https://apiproxy.shared.dev.lumesse.top/tlk/rest/user?api_key=c09f38da-3175-fcf3-b153-03130e1bca7e";

    @GetMapping(REGISTER)
    public String register() {
        return "register";
    }

    @GetMapping("/index")
    public String homepage() {
        return "index";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/login")
    public String login() {
        return "myLogin";
    }

    @PostMapping(REGISTER)
    public ResponseEntity<RegisterDto> register(RegisterDto registerDto) {
        RegisterDto savedUserDto = authenticateService.saveUser(registerDto);
        log.info(REGISTERED_SUCCESS);
        return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
    }

    @PostMapping("/apilogin")
    public ResponseEntity<String> loginApi(LoginRequestDTO loginRequestDTO) {
        authenticateService.validateLogin(loginRequestDTO);
        return new ResponseEntity<>(LOGIN_SUCCESS, HttpStatus.OK);
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public void getUser() {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("username", "SabaIntegration:gsa.lumesse:BO");
        httpHeaders.add("password", "Talentlink.@1");
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(REST_API_URL).queryParam("query", GRAPHQL_QUERY);
        String resultJson = restTemplate.exchange(builder.build().toUri()
                , HttpMethod.GET,
                entity, String.class).getBody();
//        return resultJson;
        try {
            JsonNode jsonObject = objectMapper.readTree(resultJson);
            String userData = jsonObject.get("data").get("users").get(0).toString();
            RegisterDto registerDto = objectMapper.readValue(userData, RegisterDto.class);
            registerDto.setPassword("mtedPassword");
            ResponseEntity responseEntity = register(registerDto);
            System.out.println(responseEntity.getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
//        JSONObject jsonObject = new JSONObject(resultJson);
//        boolean dohave = jsonObject.has("data");
//        if(dohave) {
//            JSONObject usersObject = jsonObject.getJSONObject("data");
//            JSONArray jsonArray = (JSONArray) usersObject.get("users");
//            JSONObject user1Object = (JSONObject) jsonArray.get(0);
//            String userJsonString = user1Object.toString();
//            try {
//                RegisterDto registerDto = objectMapper.readValue(userJsonString, RegisterDto.class);
//                System.out.println(registerDto);
////                registerDto.setPassword("mtedPassword");
////                ResponseEntity responseEntity = register(registerDto);
////                System.out.println(responseEntity.getBody());
//
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
////            System.out.println(usersObject.get("users"));
//
////            System.out.println(jsonObject.get("data['users']"));
//        }

    }
}
