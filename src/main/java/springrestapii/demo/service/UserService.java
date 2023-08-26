package springrestapii.demo.service;

import springrestapii.demo.dto.UserDto;
import springrestapii.demo.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    User findById(Long id);
    List<User> getAllUsers();
    User updateUser(User user);

    void deleteUserById(Long id);

}
