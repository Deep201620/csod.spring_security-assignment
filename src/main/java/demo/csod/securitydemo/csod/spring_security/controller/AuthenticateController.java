package demo.csod.securitydemo.csod.spring_security.controller;

import demo.csod.securitydemo.csod.spring_security.dto.LoginRequestDTO;
import demo.csod.securitydemo.csod.spring_security.dto.SourceSystemDto;
import demo.csod.securitydemo.csod.spring_security.dto.UsersDto;
import demo.csod.securitydemo.csod.spring_security.integration.IntegrationApiService;
import demo.csod.securitydemo.csod.spring_security.models.Users;
import demo.csod.securitydemo.csod.spring_security.repository.UserSourceSystemRepository;
import demo.csod.securitydemo.csod.spring_security.service.AuthenticateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static demo.csod.securitydemo.csod.spring_security.dto.SourceSystem.General;
import static demo.csod.securitydemo.csod.spring_security.dto.SourceSystem.Talentlink;


@Controller
@Slf4j
public class AuthenticateController {

    public static final String REGISTER = "/register";

    @Autowired
    AuthenticateService authenticateService;

    @Autowired
    IntegrationApiService integrationApiService;

    @Autowired
    UserSourceSystemRepository userSourceSystemRepository;


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
    public ResponseEntity<UsersDto> register(UsersDto usersDto) {
        usersDto.setCreationDate(new Date());
        usersDto.setSourceSystem(new SourceSystemDto(General));
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
    public ResponseEntity<List<UsersDto>> registerUserFromTalentlink() {
        UsersDto usersDto = null;
        List<UsersDto> usersDtoList = integrationApiService.getUsersFromTalentlink();
        for (int i = 0; i < usersDtoList.size(); i++) {
            usersDto = usersDtoList.get(i);
            usersDto.setPassword(integrationApiService.passwordGenerator());
            usersDto.setSourceSystem(new SourceSystemDto(Talentlink));
            authenticateService.saveUser(usersDto);
        }
        return new ResponseEntity<>(usersDtoList,HttpStatus.OK);
    }

    @PostMapping("/getUserDto")
    @ResponseBody
    public List<UsersDto> getUserDto() {
//        List<Users> usersList = authenticateService.findAllUsers();
        List<Users> user = authenticateService.getUsers();
        List<UsersDto> usersDtoList = new ArrayList<>();
        for (int i = 0; i < user.size(); i++) {
            UsersDto usersDto = new UsersDto();
            usersDto.setUserId(user.get(i).getUserId());
            usersDto.setUserName(user.get(i).getUserName());
            usersDto.setFirstName(user.get(i).getFirstName());
            usersDto.setLastName(user.get(i).getFirstName());
            usersDto.setEmailId(user.get(i).getEmailId());
            usersDto.setPassword(user.get(i).getPassword());
            usersDto.setLanguage(user.get(i).getLanguage());
            usersDtoList.add(usersDto);
        }
        return usersDtoList;
    }

    @RequestMapping("/createUser")
    public void sendUser() {
        integrationApiService.sendUserToTalentLink();
        authenticateService.MapUsertoTlk();
    }

}
