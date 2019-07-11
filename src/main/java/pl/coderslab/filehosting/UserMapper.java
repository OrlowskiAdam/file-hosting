package pl.coderslab.filehosting;

import org.springframework.stereotype.Component;
import pl.coderslab.filehosting.dto.UserDto;
import pl.coderslab.filehosting.entity.User;

@Component
public class UserMapper {
    public UserDto mapUserToDataObject(User user){
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .groupId(user.getGroupId())
                .email(user.getEmail())
                .build();
    }
}
