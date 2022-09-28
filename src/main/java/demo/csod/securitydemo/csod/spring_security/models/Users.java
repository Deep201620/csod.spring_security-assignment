package demo.csod.securitydemo.csod.spring_security.models;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotNull
    private String userName;

    @NotNull
    @Column(unique = true)
    private String emailId;

    @Setter(AccessLevel.NONE)
    private LocalDate creationDate;

    @NotNull
    private String password;

    public void setCreationDate(String creationDate) {
        this.creationDate = LocalDate.parse(creationDate);
    }

}
