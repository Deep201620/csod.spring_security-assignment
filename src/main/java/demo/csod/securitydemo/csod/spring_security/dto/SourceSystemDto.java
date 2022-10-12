package demo.csod.securitydemo.csod.spring_security.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import demo.csod.securitydemo.csod.spring_security.models.Users;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SourceSystemDto {

    @NotNull
    private String sourceSystem;

}
