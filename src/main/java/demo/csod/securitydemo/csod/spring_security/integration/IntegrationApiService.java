package demo.csod.securitydemo.csod.spring_security.integration;

import demo.csod.securitydemo.csod.spring_security.dto.CreateUserDto;
import demo.csod.securitydemo.csod.spring_security.dto.UsersDto;
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

    private final static String REST_API_URL = "http://localhost:8081/registerUserFromTalentlink";


    public List<UsersDto> getUsersFromTalentlink() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        List<UsersDto> usersDtoList = null;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(REST_API_URL);
        ResponseEntity<UsersDto[]> resultJson = restTemplate.exchange(builder.build().toUri()
                , HttpMethod.GET,
                entity, UsersDto[].class);
        usersDtoList = Arrays.asList(resultJson.getBody());
        return usersDtoList;
    }

    public String passwordGenerator() {
        String password = UUID.randomUUID().toString().replaceAll("-", "");
        return password;
    }

    public String sendUserToTalentLink() {
        CreateUserDto createUserDto = new CreateUserDto();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(APPLICATION_JSON));
        String postUrl = "http://localhost:8081/createUser";
        HttpEntity<CreateUserDto> entity = new HttpEntity<>(createUserDto);
        String responseEntity = restTemplate.postForObject(postUrl, entity, String.class);
        return responseEntity;
    }
}
