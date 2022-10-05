package demo.csod.securitydemo.csod.spring_security.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {

    @NotNull
    @JsonProperty(value = "login")
    private String userName;

    @NotNull
    @JsonProperty(value = "email")
    private String emailId;

    @NotNull
    private String language;

    @NotNull
    @JsonIgnore
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty(value = "creationDate")
    private Date creationDate;

    @JsonIgnore
    private String sourceSystem;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

}
