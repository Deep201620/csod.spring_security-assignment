package demo.csod.securitydemo.csod.spring_security.repository;

import demo.csod.securitydemo.csod.spring_security.models.SchedulerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulerInfoRepository extends JpaRepository<SchedulerInfo, Integer> {


}
