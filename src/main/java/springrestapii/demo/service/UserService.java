package springrestapii.demo.service;

import springrestapii.demo.entity.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    User findById(Long id);
    List<User> getAllUsers();
    User updateUser(User user);

    void deleteUserById(Long id);

}
