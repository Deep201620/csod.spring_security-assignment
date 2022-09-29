package demo.csod.securitydemo.csod.spring_security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {

    @NotNull(message = "Username cannot be null")
    @JsonProperty(value = "firstName")
    private String userName;

    @NotNull
    @JsonProperty(value = "email")
    private String emailId;

    @JsonProperty(value = "creationDate")
    private LocalDate creationDate;

    @NotNull
    @JsonIgnore
    private String password;

    @JsonIgnore
    private String sourceSystem;

    public void setCreationDate(Date date){
        this.creationDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

}
