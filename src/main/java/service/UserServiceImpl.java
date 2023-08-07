package service;

import dto.UserRegistrationDto;
import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.UserRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public  UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registration) {
        User user = new User(registration.getFirstName(),
                registration.getLastName(),
                registration.getEmailId(),
                registration.getPassword(),
                List.of(new Role("ROLE_USER")));
        return userRepository.save(user);
    }


}
