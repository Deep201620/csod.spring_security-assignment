package demo.csod.securitydemo.csod.spring_security.service;

import demo.csod.securitydemo.csod.spring_security.dto.LoginRequestDTO;
import demo.csod.securitydemo.csod.spring_security.dto.RegisterDto;
import demo.csod.securitydemo.csod.spring_security.dto.UsersDto;
import demo.csod.securitydemo.csod.spring_security.exception.ResourceNotFound;
import demo.csod.securitydemo.csod.spring_security.models.Users;
import demo.csod.securitydemo.csod.spring_security.repository.UserRepository;
import demo.csod.securitydemo.csod.spring_security.utils.dtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
    ValidatorService validatorService;

    @Autowired
    dtoMapper dtoMapperObj;

    public UsersDto saveUser(UsersDto usersDto) {
        validatorService.userExist(usersDto.getEmailId());
        Users user = dtoMapperObj.dtoToEntity(usersDto);
        log.info("User registered with email "+user.getEmailId()+" and password "+user.getPassword());
        user.setPassword(validatorService.encryptPassword(user.getPassword()));
        userRepository.save(user);
        UsersDto userDto = dtoMapperObj.entityToDto(user);
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
}
