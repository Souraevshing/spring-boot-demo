package spring.todo.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spring.todo.app.dto.TodoDto;
import spring.todo.app.service.TodoService;

import java.util.List;

// using PreAuthorize annotation to use method level security
// the string in the argument tells which type of user be it USER or ADMIN can access the specific routes.

@RestController
@RequestMapping("api/v1/todos")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        TodoDto savedTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable("id") Long id) {
        TodoDto todoDto = todoService.getTodoById(id);
        return new ResponseEntity<TodoDto>(todoDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> allTodos = todoService.getAllTodos();
        return new ResponseEntity<List<TodoDto>>(allTodos, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long id) {
        TodoDto updatedTodo = todoService.updateTodo(todoDto, id);
        return new ResponseEntity<TodoDto>(updatedTodo, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDto(TodoDto todoDto,@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping ("{id}/complete")
    public  ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long id) {
        TodoDto completeTodo = todoService.completeTodo(id);
        return new ResponseEntity<TodoDto>(completeTodo, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping ("{id}/incomplete")
    public  ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long id) {
        TodoDto completeTodo = todoService.inCompleteTodo(id);
        return new ResponseEntity<TodoDto>(completeTodo, HttpStatus.OK);
    }

}
