package demo.csod.securitydemo.csod.spring_security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    private String userName;

    private String emailId;

    private LocalDate bDate;

    private String password;

    public void setbDate(String bDate) {
        this.bDate = LocalDate.parse(bDate);
    }

}
