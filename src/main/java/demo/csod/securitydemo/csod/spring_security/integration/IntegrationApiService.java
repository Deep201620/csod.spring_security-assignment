package demo.csod.securitydemo.csod.spring_security.integration;

import demo.csod.securitydemo.csod.spring_security.dto.RegisterDto;
import demo.csod.securitydemo.csod.spring_security.dto.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class IntegrationApiService {

    @Autowired
    RestTemplate restTemplate;

    //    private final static String GRAPHQL_QUERY = "{ users (where: {id_eq: 1}){firstName email creationDate}}";
    private final static String GRAPHQL_QUERY = "{\n" +
            "  users(first: 100, after: 33800, where: {inactive_eq: false}) {\n" +
            "    firstName\n" +
            "    email\n" +
            "    creationDate\n" +
            "  }\n" +
            "}";
    private final static String REST_API_URL = "https://apiproxy.shared.dev.lumesse.top/tlk/rest/user?api_key=c09f38da-3175-fcf3-b153-03130e1bca7e";


    public List<UsersDto> getUsersFromTalentlink() {
        HttpHeaders httpHeaders = settingHttpHeaders();
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
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

    public String passwordGenerator(){
        String password = UUID.randomUUID().toString().replaceAll("-","");
     return password;
    }
}
