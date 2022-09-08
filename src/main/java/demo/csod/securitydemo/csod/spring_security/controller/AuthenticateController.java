package demo.csod.securitydemo.csod.spring_security.controller;

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
        log.info("User Registered Successfully");
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(LoginRequestDTO loginRequestDTO) {
//            userDetailService.loadUserByUsername(loginRequestDTO.getEmailId());
//            return new ResponseEntity<>(LOGIN_SUCCESS, HttpStatus.OK);
////        return authenticateService.validLogin(loginRequestDTO);
//    }

}
