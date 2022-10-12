package demo.csod.securitydemo.csod.spring_security.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String language;


    @NotNull(message = "Username cannot be null")
    @JsonProperty(value = "login")
    private String userName;


    public Users(String firstName, String lastName, String userName, String emailId, String language, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.language = language;
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
    }

    @NotNull
    @Column(unique = true)
    @JsonProperty(value = "email")
    private String emailId;

    private Date creationDate;

    @NotNull
    private String password;

    @OneToOne(mappedBy = "user",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    private UserSourceSystem sourceSystem;


    public Users(String firstName, String lastName, String language, String userName, String emailId, Date creationDate, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.language = language;
        this.userName = userName;
        this.emailId = emailId;
        this.creationDate = creationDate;
        this.password = password;
    }
}
