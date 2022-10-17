package demo.csod.securitydemo.csod.spring_security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SourceSystemDto {

    @NotNull
    private SourceSystem sourceSystem;
}
