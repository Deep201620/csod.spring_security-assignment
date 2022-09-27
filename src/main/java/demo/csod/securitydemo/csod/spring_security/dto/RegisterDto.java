package demo.csod.securitydemo.csod.spring_security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @NotNull
    @JsonProperty(value = "firstName")
    private String userName;

    @NotNull
    @JsonProperty(value = "email")
    private String emailId;

    @JsonProperty(value = "creationDate")
    private LocalDate bDate;

    @NotNull
    @JsonIgnore
    private String password;

    public void setbDate(Date bDate) {
        this.bDate = bDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

}
