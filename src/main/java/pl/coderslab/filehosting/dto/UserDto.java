package pl.coderslab.filehosting.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private Long groupId;
    private String login;
    private String email;
    private String firstName;
    private String lastName;
}
