package demo.csod.securitydemo.csod.spring_security.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @JsonProperty(value = "firstName")
    @NotNull
    private String userName;

    @NotNull
    @Column(unique = true)
    @JsonProperty(value = "email")
    private String emailId;


    @JsonProperty(value = "creationDate")
    private LocalDate creationDate;

    @NotNull
    @JsonIgnore
    private String password;

    @NotNull
    @JsonIgnore
    private String sourceSystem;


//    public void setCreationDate(Date date){
//        this.creationDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//    }
//
//    public LocalDate getCreationDate() {
//        return creationDate;
//    }
}
