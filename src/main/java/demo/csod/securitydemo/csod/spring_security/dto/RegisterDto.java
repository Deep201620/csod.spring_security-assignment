package demo.csod.securitydemo.csod.spring_security.dto;

import com.sun.istack.NotNull;
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

    @NotNull
    private String userName;

    @NotNull
    private String emailId;

    private LocalDate bDate;

    @NotNull
    private String password;

    public void setbDate(String bDate) {
        this.bDate = LocalDate.parse(bDate);
    }

}
