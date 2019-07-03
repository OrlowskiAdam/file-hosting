package pl.coderslab.filehosting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.filehosting.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByEmail(String email);
    User findFirstByLogin(String login);
}
