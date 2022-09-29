package demo.csod.securitydemo.csod.spring_security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataDto {

    @JsonProperty("users")
    private ArrayList<UsersDto> users;
}
