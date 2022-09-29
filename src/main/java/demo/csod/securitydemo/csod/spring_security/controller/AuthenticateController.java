package demo.csod.securitydemo.csod.spring_security.controller;

import demo.csod.securitydemo.csod.spring_security.dto.LoginRequestDTO;
import demo.csod.securitydemo.csod.spring_security.dto.UsersDto;
import demo.csod.securitydemo.csod.spring_security.integration.IntegrationApiService;
import demo.csod.securitydemo.csod.spring_security.service.AuthenticateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@Slf4j
public class AuthenticateController {

    public static final String REGISTER = "/register";

    @Autowired
    AuthenticateService authenticateService;

    @Autowired
    IntegrationApiService integrationApiService;


    private final String REGISTERED_SUCCESS = "User Registered Successfully";
    private final String LOGIN_SUCCESS = "User Logged in";

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

    @PostMapping(value = REGISTER)
    public ResponseEntity<UsersDto> register(@RequestBody  UsersDto usersDto) {
        usersDto.setSourceSystem("General");
        UsersDto savedUserDto = authenticateService.saveUser(usersDto);
        log.info(REGISTERED_SUCCESS);
        return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
    }

    @PostMapping("/apilogin")
    public ResponseEntity<String> loginApi(LoginRequestDTO loginRequestDTO) {
        authenticateService.validateLogin(loginRequestDTO);
        return new ResponseEntity<>(LOGIN_SUCCESS, HttpStatus.OK);
    }

    @RequestMapping(value = "/registerUserFromTalentlink")
    @ResponseBody
    public void registerUserFromTalentlink() {
        List<UsersDto> usersDtoList = integrationApiService.getUsersFromTalentlink();
        for (int i = 0; i < usersDtoList.size(); i++) {
            UsersDto usersDto = usersDtoList.get(i);
            usersDto.setSourceSystem("Talentlink");
            usersDto.setPassword(integrationApiService.passwordGenerator());
            authenticateService.saveUser(usersDto);
        }
    }
}
