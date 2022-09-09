package demo.csod.securitydemo.csod.spring_security.dto;

import com.sun.istack.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @NotNull
    private String emailId;

    @NotNull
    private String password;

}
