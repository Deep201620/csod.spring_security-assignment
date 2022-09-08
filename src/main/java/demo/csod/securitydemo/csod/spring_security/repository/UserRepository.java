package demo.csod.securitydemo.csod.spring_security.repository;

import demo.csod.securitydemo.csod.spring_security.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

    Optional<Users> findByEmailId(String emailId);

}
