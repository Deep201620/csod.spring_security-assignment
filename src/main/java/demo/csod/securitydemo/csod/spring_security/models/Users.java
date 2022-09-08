package demo.csod.securitydemo.csod.spring_security.models;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotNull
    private String userName;

    @NotNull
    @Column(unique = true)
    private String emailId;

    @Setter(AccessLevel.PROTECTED)
    private LocalDate bDate;

    @NotNull
    private String password;

    public void setbDate(String bDate) {
        this.bDate = LocalDate.parse(bDate);
    }

}
