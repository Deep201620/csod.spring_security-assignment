package demo.csod.securitydemo.csod.spring_security.utils;
import demo.csod.securitydemo.csod.spring_security.dto.RegisterDto;
import demo.csod.securitydemo.csod.spring_security.models.Users;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class dtoMapper {

    private final static ModelMapper modelMapper = new ModelMapper();

    public RegisterDto entityToDto(Users users) {
        return modelMapper.map(users, RegisterDto.class);
    }

    public Users dtoToEntity(RegisterDto registerDto) {
        return modelMapper.map(registerDto, Users.class);
    }

}
