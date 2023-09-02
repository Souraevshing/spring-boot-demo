package spring.todo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.todo.app.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long> {



}
