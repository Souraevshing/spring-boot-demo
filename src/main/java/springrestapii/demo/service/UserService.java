package springrestapii.demo.service;

import springrestapii.demo.dto.UserDto;
import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto findById(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto user);
    void deleteUserById(Long id);

}
