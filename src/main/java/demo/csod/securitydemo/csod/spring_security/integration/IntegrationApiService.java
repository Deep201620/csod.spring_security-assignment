package demo.csod.securitydemo.csod.spring_security.integration;

import demo.csod.securitydemo.csod.spring_security.config.JwtHelper;
import demo.csod.securitydemo.csod.spring_security.dto.CreateUserDto;
import demo.csod.securitydemo.csod.spring_security.dto.LoginRequestDTO;
import demo.csod.securitydemo.csod.spring_security.dto.UsersDto;
import demo.csod.securitydemo.csod.spring_security.service.MyUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Service
public class IntegrationApiService {

    @Autowired
    RestTemplate restTemplate;

    private final String REST_API_URL = "http://localhost:8081/registerUserFromTalentlink";
    private final String GET_TLK_ID = "http://localhost:8081/getTlkId";
    private final String SEND_USER_TO_TLK = "http://localhost:8081/sendUser";

    private UserDetails userDetails;
    private String jwtToken;


    private final JwtHelper jwtHelper;
    private final MyUserDetailService myUserDetailService;
    private final PasswordEncoder passwordEncoder;

    public IntegrationApiService(JwtHelper jwtHelper, MyUserDetailService myUserDetailService, PasswordEncoder passwordEncoder) {
        this.jwtHelper = jwtHelper;
        this.myUserDetailService = myUserDetailService;
        this.passwordEncoder = passwordEncoder;
    }


    public List<UsersDto> getUsersFromTalentlink() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(APPLICATION_JSON));
        httpHeaders.set("Authorization", "Bearer " + jwtToken);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        List<UsersDto> usersDtoList = null;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(REST_API_URL);
        ResponseEntity<UsersDto[]> resultJson = restTemplate.exchange(builder.build().toUri()
                , HttpMethod.GET,
                entity, UsersDto[].class);
        usersDtoList = Arrays.asList(resultJson.getBody());

        return usersDtoList;
    }

    public Map getTlkId() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + jwtToken);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(GET_TLK_ID);
        ResponseEntity<Map> result = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, Map.class);
        return result.getBody();
    }

    public String passwordGenerator() {
        String password = UUID.randomUUID().toString().replaceAll("-", "");
        return password;
    }

    public String sendUserToTalentLink() {
        CreateUserDto createUserDto = new CreateUserDto();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(APPLICATION_JSON));
        httpHeaders.set("Authorization", "Bearer " + jwtToken);
        HttpEntity<CreateUserDto> entity = new HttpEntity<>(createUserDto, httpHeaders);
        String responseEntity = restTemplate.postForObject(SEND_USER_TO_TLK, entity, String.class);
        return responseEntity;
    }

    public void login(LoginRequestDTO loginRequestDTO) {
        userDetails = myUserDetailService.loadUserByUsername(loginRequestDTO.getEmailId());
        if (passwordEncoder.matches(loginRequestDTO.getPassword(), userDetails.getPassword())) {
            jwtToken = generateToken(loginRequestDTO.getEmailId());
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("User with email {} logged in", userDetails.getUsername());
            log.info(jwtToken);
        }
    }

    private String generateToken(String emailId) {
        Map<String, String> claims = new HashMap<>();
        claims.put("username", emailId);
        return jwtHelper.createJwtForClaims(emailId, claims);
    }
}
