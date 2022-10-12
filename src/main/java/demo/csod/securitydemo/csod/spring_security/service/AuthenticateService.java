package demo.csod.securitydemo.csod.spring_security.service;

import demo.csod.securitydemo.csod.spring_security.dto.LoginRequestDTO;
import demo.csod.securitydemo.csod.spring_security.dto.UsersDto;
import demo.csod.securitydemo.csod.spring_security.exception.ResourceNotFound;
import demo.csod.securitydemo.csod.spring_security.models.SchedulerInfo;
import demo.csod.securitydemo.csod.spring_security.models.UserSourceSystem;
import demo.csod.securitydemo.csod.spring_security.models.Users;
import demo.csod.securitydemo.csod.spring_security.repository.SchedulerInfoRepository;
import demo.csod.securitydemo.csod.spring_security.repository.UserRepository;
import demo.csod.securitydemo.csod.spring_security.repository.UserSourceSystemRepository;
import demo.csod.securitydemo.csod.spring_security.utils.dtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AuthenticateService {

    public static final String LOGIN_SUCCESSFUL = "Login Successful";
    public static final String INCORRECT_CREDENTIALS = "Username/password is incorrect";

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserSourceSystemRepository userSourceSystemRepository;

    @Autowired
    SchedulerInfoRepository schedulerInfoRepository;

    @Autowired
    ValidatorService validatorService;

    @Autowired
    dtoMapper dtoMapperObj;

    public UsersDto saveUser(UsersDto usersDto) {
        validatorService.userExist(usersDto.getEmailId());
        Users user = dtoMapperObj.dtoToEntity(usersDto);
        log.info("User registered with email " + user.getEmailId() + " and password " + user.getPassword());
        user.setPassword(validatorService.encryptPassword(user.getPassword()));
        UserSourceSystem userSourceSystem = new UserSourceSystem();
        userSourceSystem.setSourceSystem(usersDto.getSourceSystem().getSourceSystem());
        userSourceSystem.setUser(user);
        user.setSourceSystem(userSourceSystem);
        Users savedUser = userRepository.save(user);
        UsersDto userDto = dtoMapperObj.entityToDto(savedUser);
        return userDto;
    }

    public void validateLogin(LoginRequestDTO loginRequestDTO) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmailId(),
                            loginRequestDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info(LOGIN_SUCCESSFUL);
        } catch (BadCredentialsException e) {
            throw new ResourceNotFound(INCORRECT_CREDENTIALS, loginRequestDTO.getEmailId());
        }
    }

    public Users getUsers() {
        List<UserSourceSystem> usersList = userSourceSystemRepository.findAllGeneralUsers();
        List<UserSourceSystem> userSourceSystemList;
        Users user = null;
        if (!(usersList.size() == 0)) {
            userSourceSystemList = findNullusers();
            for (int i = 0; i < userSourceSystemList.size(); i++) {
                user = userSourceSystemList.get(i).getUser();
                return user;
            }
        } else {
            throw new ResourceNotFound("No users found", "No user exists with SourceSystem General");
        }
        return user;
    }

    private List<UserSourceSystem> findNullusers(){
        List<UserSourceSystem> userSourceSystemList = userSourceSystemRepository.findNullUserId();
        if(CollectionUtils.isEmpty(userSourceSystemList)){
            throw new ResourceNotFound("No users found", "No user exists with SourceSystem General");
        }
        for(UserSourceSystem user: userSourceSystemList){
            System.out.println(user);
        }
        return userSourceSystemList;
    }
}
