package demo.csod.securitydemo.csod.spring_security.controller;

import demo.csod.securitydemo.csod.spring_security.dto.LoginRequestDTO;
import demo.csod.securitydemo.csod.spring_security.dto.RegisterDto;
import demo.csod.securitydemo.csod.spring_security.models.Users;
import demo.csod.securitydemo.csod.spring_security.service.AuthenticateService;
import demo.csod.securitydemo.csod.spring_security.utils.dtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class AuthenticateController {

    @Autowired
    AuthenticateService authenticateService;

    @Autowired
    dtoMapper dtoMapperobj;

    private final String REGISTERED_SUCCESS = "User Registered Successfully";
    private final String LOGIN_SUCCESS = "User Logged in";

    @GetMapping("/register")
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

    @PostMapping("/register")
    public ResponseEntity<RegisterDto> register(RegisterDto registerDto) {
        Users user = dtoMapperobj.dtoToEntity(registerDto);
        Users registeredUser = authenticateService.saveUser(user);
        RegisterDto userDto = dtoMapperobj.entityToDto(registeredUser);
        log.info(REGISTERED_SUCCESS);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/apilogin")
    public ResponseEntity<String> loginApi(LoginRequestDTO loginRequestDTO) {
        authenticateService.validLogin(loginRequestDTO);
        return new ResponseEntity<>(LOGIN_SUCCESS, HttpStatus.OK);
    }
}
