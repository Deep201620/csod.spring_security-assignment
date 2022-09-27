package demo.csod.securitydemo.csod.spring_security.service;

import demo.csod.securitydemo.csod.spring_security.models.Users;
import demo.csod.securitydemo.csod.spring_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static demo.csod.securitydemo.csod.spring_security.exception.IErrorMessage.USER_NOT_EXIST;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        Optional<Users> user = Optional.of(userRepository.findByEmailId(emailId)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_EXIST)));
        return user.map(MyUserDetails::new).get();
    }
}
