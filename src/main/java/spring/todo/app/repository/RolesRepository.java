package spring.todo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.todo.app.entity.Role;

public interface RolesRepository extends JpaRepository<Role, Long> {



}
