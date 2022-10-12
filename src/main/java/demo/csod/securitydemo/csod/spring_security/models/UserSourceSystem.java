package demo.csod.securitydemo.csod.spring_security.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSourceSystem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int systemId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users user;

    @NotNull
    private String sourceSystem;

    public UserSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    private String tlkUserId;
}
