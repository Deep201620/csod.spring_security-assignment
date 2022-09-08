package demo.csod.securitydemo.csod.spring_security.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    private String emailId;
    private String password;

}
