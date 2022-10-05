package demo.csod.securitydemo.csod.spring_security.integration;

import demo.csod.securitydemo.csod.spring_security.dto.CreateUserDto;
import demo.csod.securitydemo.csod.spring_security.dto.RegisterDto;
import demo.csod.securitydemo.csod.spring_security.dto.UsersDto;
import demo.csod.securitydemo.csod.spring_security.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class IntegrationApiService {

    @Autowired
    RestTemplate restTemplate;

    //    private final static String GRAPHQL_QUERY = "{ users (where: {id_eq: 1}){firstName email creationDate}}";
    private final static String GRAPHQL_QUERY = "{\n" +
            "  users(first: 100, after: 33800, where: {inactive_eq: false}) {\n" +
            "    firstName\n" +
            "    lastName\n" +
            "    login\n" +
            "    language\n" +
            "    email\n" +
            "    creationDate\n" +
            "  }\n" +
            "}";
    private final static String REST_API_URL = "https://apiproxy.shared.dev.lumesse.top/tlk/rest/user?api_key=c09f38da-3175-fcf3-b153-03130e1bca7e";


    public List<UsersDto> getUsersFromTalentlink() {
        HttpHeaders httpHeaders = settingHttpHeaders();
        httpHeaders.setAccept(Arrays.asList(APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        List<UsersDto> usersDtoList = null;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(REST_API_URL).queryParam("query", GRAPHQL_QUERY);
        ResponseEntity<RegisterDto> resultJson = restTemplate.exchange(builder.build().toUri()
                , HttpMethod.GET,
                entity, RegisterDto.class);
        usersDtoList = resultJson.getBody().getData().getUsers();
        return usersDtoList;
    }

    private HttpHeaders settingHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("username", "SabaIntegration:gsa.lumesse:BO");
        httpHeaders.add("password", "Talentlink.@1");
        return httpHeaders;
    }

    public String passwordGenerator() {
        String password = UUID.randomUUID().toString().replaceAll("-", "");
        return password;
    }

    public String sendUserToTalentLink(CreateUserDto createUserDto) {
        HttpHeaders httpHeaders = settingHttpHeaders();
        String postUrl = "https://apiproxy.shared.dev.lumesse.top/tlk/rest/user?api_key=c09f38da-3175-fcf3-b153-03130e1bca7e";
        HttpEntity<CreateUserDto> entity = new HttpEntity<>(createUserDto, httpHeaders);
        String responseEntity = restTemplate.postForObject(postUrl, entity, String.class);
        return responseEntity;
    }

    public CreateUserDto setUserDetails(CreateUserDto createUserDto, List<Users> users, int i){
        createUserDto.setFirstName(users.get(i).getFirstName());
        createUserDto.setLastName(users.get(i).getLastName());
        createUserDto.setUserName(users.get(i).getUserName());
        createUserDto.setEmail(users.get(i).getEmailId());
        createUserDto.setUserActivation("ACTIVATEUSERNOWWITHOUTSENDINGEMAILNOTIFICATION");
        createUserDto.setLanguage(users.get(i).getLanguage());
        createUserDto.setPassword(users.get(i).getPassword());
        return createUserDto;
    }
}
