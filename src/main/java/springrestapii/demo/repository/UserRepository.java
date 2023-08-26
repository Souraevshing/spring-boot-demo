package springrestapii.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springrestapii.demo.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
