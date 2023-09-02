package spring.todo.app.repository;

import spring.todo.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getByUsername(String username);

    Optional<User> getByUsernameOrEmail(String username, String email);

}
