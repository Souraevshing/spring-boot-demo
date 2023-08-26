package springrestapii.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import springrestapii.demo.dto.UserDto;
import springrestapii.demo.exception.Error;
import springrestapii.demo.exception.ResourceNotFound;
import springrestapii.demo.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserDto savedUser = userService.createUser(user);
        return  new ResponseEntity<UserDto>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
        UserDto user = userService.findById(id);
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<List<UserDto>>(allUsers,HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto user) {
        user.setId(id);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<UserDto>(updatedUser,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    //@ExceptionHandler is used to throw custom exception like here we are throwing custom error message, code.
    //In parameter of @ExceptionHandler we pass Exception class name which we want to throw, here we are throwing exception from
    //ResourceNotFound class

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Error> handleError(ResourceNotFound resourceNotFound, WebRequest request) {
        Error errorDetails = new Error(
                LocalDateTime.now(),
                resourceNotFound.getMessage(),
                request.getDescription(false),
                404L
        );

        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }

}
