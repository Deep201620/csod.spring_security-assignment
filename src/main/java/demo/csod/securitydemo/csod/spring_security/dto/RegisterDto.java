package demo.csod.securitydemo.csod.spring_security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    //    @NotNull
//    @JsonProperty(value = "firstName")
//    private String userName;
//
//    @NotNull
//    @JsonProperty(value = "email")
//    private String emailId;
//
//    @JsonProperty(value = "creationDate")
//    private LocalDate creationDate;
//
//    @NotNull
//    @JsonIgnore
//    private String password;
//
//    public void setCreationDate(Date creationDate) {
//        this.creationDate = creationDate.toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDate();
//    }
    @JsonProperty("data")
    private DataDto data;
}
