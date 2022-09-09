package demo.csod.securitydemo.csod.spring_security.service;

import demo.csod.securitydemo.csod.spring_security.dto.LoginRequestDTO;
import demo.csod.securitydemo.csod.spring_security.exception.ResourceNotFound;
import demo.csod.securitydemo.csod.spring_security.models.Users;
import demo.csod.securitydemo.csod.spring_security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticateService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ValidatorService validatorService;

    public Users saveUser(Users user) {
        validatorService.userExist(user.getEmailId());
        user.setPassword(validatorService.encryptPassword(user.getPassword()));
        return userRepository.save(user);
    }

//    public ResponseEntity<String> validLogin(LoginRequestDTO loginRequestDTO) {
//        String emailId = loginRequestDTO.getEmailId();
//        String password = loginRequestDTO.getPassword();
//        if (validatorService.authenticateUser(emailId, password))
//            return new ResponseEntity<>(LOGIN_SUCCESS, HttpStatus.OK);
//        return new ResponseEntity<>(INVALID_CREDENTIALS, HttpStatus.NOT_FOUND);
//    }

    public void validLogin(LoginRequestDTO loginRequestDTO){
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmailId(),
                            loginRequestDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("Login Successful");
        } catch (BadCredentialsException e) {
            throw new ResourceNotFound("Username/password is incorrect", loginRequestDTO.getEmailId());
        }
    }
}
