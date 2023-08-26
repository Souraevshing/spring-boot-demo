package springrestapii.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springrestapii.demo.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    //custom jpa query to find user by email
    Optional<User> findByEmail(String email);

}
