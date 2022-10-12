package demo.csod.securitydemo.csod.spring_security.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchedulerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduler_Id;

    private Date schedulerRuntime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId",referencedColumnName = "userId")
    private Users user;
}
