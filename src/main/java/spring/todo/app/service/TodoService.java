package spring.todo.app.service;

import spring.todo.app.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);
    List<TodoDto> getAllTodos();
    TodoDto getTodoById(Long id);
    TodoDto updateTodo(TodoDto todoDto, Long id);
    void deleteTodo(Long id);
    TodoDto completeTodo(Long id);
    TodoDto inCompleteTodo(Long id);

}
