package demo.csod.securitydemo.csod.spring_security.utils;

import demo.csod.securitydemo.csod.spring_security.dto.SourceSystemDto;
import demo.csod.securitydemo.csod.spring_security.dto.UsersDto;
import demo.csod.securitydemo.csod.spring_security.models.UserSourceSystem;
import demo.csod.securitydemo.csod.spring_security.models.Users;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class dtoMapper {
    private final static ModelMapper modelMapper = new ModelMapper();

    public UsersDto entityToDto(Users users) {
        return modelMapper.map(users, UsersDto.class);
    }

    public Users dtoToEntity(UsersDto usersDto) {
        return modelMapper.map(usersDto, Users.class);
    }
}
