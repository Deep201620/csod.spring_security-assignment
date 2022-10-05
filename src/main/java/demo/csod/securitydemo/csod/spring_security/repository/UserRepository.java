package demo.csod.securitydemo.csod.spring_security.repository;

import demo.csod.securitydemo.csod.spring_security.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

    Optional<Users> findByEmailId(String emailId);

    @Query("select new Users(firstName,lastName,userName,emailId,language,password) from Users where sourceSystem = :sourceSystem")
    List<Users> findAllGeneralUsers(@Param("sourceSystem") String sourceSystem);
}
