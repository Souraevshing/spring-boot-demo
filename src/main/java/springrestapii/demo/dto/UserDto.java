package springrestapii.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "UserDto model"
)
public class UserDto {

    private Long id;

    @Schema(
            description = "User First Name"
    )
    @NotEmpty(message = "First name should not be empty")
    @NotNull(message = "First name should not be null")
    private String firstName;

    @Schema(
            description = "User Last Name"
    )
    @NotEmpty(message = "Last name should not be empty")
    @NotNull(message = "Last name should not be null")
    private String lastName;

    @Schema(
            description = "User Email Id"
    )
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be a valid email id")
    @NotNull(message = "Email should not be null")
    private String email;

}
