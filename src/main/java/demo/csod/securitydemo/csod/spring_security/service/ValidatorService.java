package demo.csod.securitydemo.csod.spring_security.service;

import demo.csod.securitydemo.csod.spring_security.exception.UserAlreadyExists;
import demo.csod.securitydemo.csod.spring_security.models.Users;
import demo.csod.securitydemo.csod.spring_security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ValidatorService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final String USER_EXIST = "User exists with same emailId";

    public boolean userExist(String emailId) {
        Optional<Users> user = userRepository.findByEmailId(emailId);
        if (user.isEmpty())
            return false;
        throw new UserAlreadyExists(101, USER_EXIST);
    }

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }


//    public boolean authenticateUser(String emailId, String password) {
//        Optional<Users> user = Optional.of(userRepository.findByEmailId(emailId).
//                orElseThrow(() -> new ResourceNotFound(USER_NOT_FOUND,emailId)));
//        return matchPassword(user, password);
//    }

    //    public boolean matchPassword(Optional<Users> user, String loginPassword) {
//        String savedPassword = user.get().getPassword();
//        return passwordEncoder.matches(loginPassword, savedPassword);
//    }
}
