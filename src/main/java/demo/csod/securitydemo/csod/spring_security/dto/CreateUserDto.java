package demo.csod.securitydemo.csod.spring_security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.MultiValueMap;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto{

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @JsonProperty(value = "login")
    private String userName;

    @NotNull
    private String email;

    @NotNull
    private String language;

    @NotNull
    private String password;

    @NotNull
    private String userActivation;

    @NotNull
    private SourceSystemDto sourceSystem;
}
