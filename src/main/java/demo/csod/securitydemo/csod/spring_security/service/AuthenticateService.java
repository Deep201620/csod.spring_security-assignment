package demo.csod.securitydemo.csod.spring_security.service;

import demo.csod.securitydemo.csod.spring_security.dto.LoginRequestDTO;
import demo.csod.securitydemo.csod.spring_security.models.Users;
import demo.csod.securitydemo.csod.spring_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static demo.csod.securitydemo.csod.spring_security.utils.Iconstant.INVALID_CREDENTIALS;
import static demo.csod.securitydemo.csod.spring_security.utils.Iconstant.LOGIN_SUCCESS;

@Service
public class AuthenticateService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ValidatorService validatorService;

    public Users saveUser(Users user) {
        validatorService.userExist(user.getEmailId());
        user.setPassword(validatorService.encryptPassword(user.getPassword()));
        return userRepository.save(user);
    }

    public ResponseEntity<String> validLogin(LoginRequestDTO loginRequestDTO) {
        String emailId = loginRequestDTO.getEmailId();
        String password = loginRequestDTO.getPassword();
        if (validatorService.authenticateUser(emailId, password))
            return new ResponseEntity<>(LOGIN_SUCCESS, HttpStatus.OK);
        return new ResponseEntity<>(INVALID_CREDENTIALS, HttpStatus.NOT_FOUND);
    }
}
