package springrestapii.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springrestapii.demo.dto.UserDto;
import springrestapii.demo.service.UserService;
import java.util.List;
import java.util.Locale;

//Used @Valid annotation with UserDto class to validate fields before sending request.
//Validation will be done as mentioned in UserDto class.

@Tag(
        name = "RESTApi for User",
        description = "CRUD operations for user"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private UserService userService;

    @Operation(
            summary = "Create user",
            description = "Create user and save into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user) {
        UserDto savedUser = userService.createUser(user);
        return  new ResponseEntity<UserDto>(savedUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get user by id",
            description = "Get user by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
        UserDto user = userService.findById(id);
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }

    @Operation(
            summary = "Get all users",
            description = "Get all users"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<List<UserDto>>(allUsers,HttpStatus.OK);
    }

    @Operation(
            summary = "Update user",
            description = "Update user by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200 UPDATED"
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto user) {
        user.setId(id);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<UserDto>(updatedUser,HttpStatus.OK);
    }

    @Operation(
            summary = "Delete user",
            description = "Delete user by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200 DELETED"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

}
