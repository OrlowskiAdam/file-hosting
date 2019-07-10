package pl.coderslab.filehosting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long groupId = 1L;

    @NotBlank
    @Size(min = 8)
    @Column(unique = true)
    private String login;

    @NotBlank
    @Size(min = 8)
    private String password;

    @Email
//    @NotBlank(groups = UserRegisterValidationGroup.class)
    private String email;

    private String firstName;
    private String lastName;
}
