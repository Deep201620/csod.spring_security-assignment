package demo.csod.securitydemo.csod.spring_security.repository;

import demo.csod.securitydemo.csod.spring_security.models.UserSourceSystem;
import demo.csod.securitydemo.csod.spring_security.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQuery;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmailId(String emailId);

}
