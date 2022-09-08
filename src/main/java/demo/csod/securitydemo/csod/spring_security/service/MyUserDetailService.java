package demo.csod.securitydemo.csod.spring_security.service;

import demo.csod.securitydemo.csod.spring_security.exception.ResourceNotFound;
import demo.csod.securitydemo.csod.spring_security.models.Users;
import demo.csod.securitydemo.csod.spring_security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@Slf4j
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        Optional<Users> user = Optional.of(userRepository.findByEmailId(emailId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email ")));
        log.info("User loggedin successfully");
        return user.map(MyUserDetails::new).get();
    }
}
