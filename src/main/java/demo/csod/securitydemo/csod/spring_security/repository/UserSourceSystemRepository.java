package demo.csod.securitydemo.csod.spring_security.repository;

import demo.csod.securitydemo.csod.spring_security.models.UserSourceSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserSourceSystemRepository extends JpaRepository<UserSourceSystem, Integer> {


    @Query("from UserSourceSystem uss join uss.user where uss.sourceSystem=:sourceSystem")
    List<UserSourceSystem> findAllGeneralUsers(@Param("sourceSystem") String sourceSystem);

    @Query("from UserSourceSystem where tlkUserId is null and sourceSystem = 'General'")
    List<UserSourceSystem> findNullUserId();

}
