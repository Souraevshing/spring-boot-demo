package spring.todo.app.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spring.todo.app.dto.TodoDto;
import spring.todo.app.entity.Todo;
import spring.todo.app.exception.ResourceNotFound;
import spring.todo.app.repository.TodoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService{

    private ModelMapper modelMapper;
    private TodoRepository todoRepository;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        // convert dto to jpa entity.
        Todo todo = modelMapper.map(todoDto, Todo.class);

        // save jpa entity
        Todo savedTodo = todoRepository.save(todo);

        // convert saved jpa entity to dto object
        return modelMapper.map(savedTodo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos
                .stream()
                .map((todo) -> modelMapper.map(todo,TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found with id " + id)
                );
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo existingTodo = todoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("User not found with id "+ id)
        );
        existingTodo.setTitle(todoDto.getTitle());
        existingTodo.setDescription(todoDto.getDescription());
        existingTodo.setCompleted(todoDto.isCompleted());

        Todo updatedTodo = todoRepository.save(existingTodo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo existingTodo = todoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("User not found with id "+ id)
        );

        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found with id " + id)
                );
        todo.setCompleted(Boolean.TRUE);
        Todo completeTodo = todoRepository.save(todo);
        return modelMapper.map(completeTodo, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found with id " + id)
                );
        todo.setCompleted(Boolean.FALSE);
        Todo completeTodo = todoRepository.save(todo);
        return modelMapper.map(completeTodo, TodoDto.class);
    }
}
