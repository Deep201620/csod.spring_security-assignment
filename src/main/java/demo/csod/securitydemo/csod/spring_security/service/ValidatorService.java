package demo.csod.securitydemo.csod.spring_security.service;

import demo.csod.securitydemo.csod.spring_security.exception.UserAlreadyExists;
import demo.csod.securitydemo.csod.spring_security.models.Users;
import demo.csod.securitydemo.csod.spring_security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static demo.csod.securitydemo.csod.spring_security.exception.IErrorMessage.USER_EXISTS;

@Slf4j
@Service
public class ValidatorService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean userExist(String emailId) {
        Optional<Users> user = userRepository.findByEmailId(emailId);
        if (user.isEmpty())
            return false;
        throw new UserAlreadyExists(101, USER_EXISTS);
    }

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
