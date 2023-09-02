package spring.todo.app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {

    private Long id;

    @NotEmpty(message = "Title should not be empty")
    @NotNull(message = "Title should not be null")
    private String title;

    @NotEmpty(message = "Description should not be empty")
    @NotNull(message = "Description should not be null")
    private String description;

    @NotEmpty(message = "Completed should not be empty")
    private boolean isCompleted;
}
