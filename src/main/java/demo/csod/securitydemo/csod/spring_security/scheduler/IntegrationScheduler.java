package demo.csod.securitydemo.csod.spring_security.scheduler;


import demo.csod.securitydemo.csod.spring_security.dto.SourceSystemDto;
import demo.csod.securitydemo.csod.spring_security.dto.SourceSystem;
import demo.csod.securitydemo.csod.spring_security.dto.UsersDto;
import demo.csod.securitydemo.csod.spring_security.integration.IntegrationApiService;
import demo.csod.securitydemo.csod.spring_security.service.AuthenticateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Component
@Slf4j
public class IntegrationScheduler {

    @Autowired
    IntegrationApiService integrationApiService;

    @Autowired
    AuthenticateService authenticateService;

    @RequestMapping(value = "/registerUserFromTalentlink")
    @ResponseBody
    public void registerUserFromTalentlink() {
        List<UsersDto> usersDtoList = integrationApiService.getUsersFromTalentlink();
        for (int i = 0; i < usersDtoList.size(); i++) {
            UsersDto usersDto = usersDtoList.get(i);
            usersDto.setPassword(integrationApiService.passwordGenerator());
            usersDto.setSourceSystem(new SourceSystemDto(SourceSystem.Talentlink));
            authenticateService.saveUser(usersDto);
        }
    }

    @RequestMapping("/createUser")
    @ResponseBody
//    @Scheduled(cron = "${postuser}")
    public ResponseEntity<Void> sendUser() {
        integrationApiService.sendUserToTalentLink();
        return  ResponseEntity.ok().build();
    }
}
