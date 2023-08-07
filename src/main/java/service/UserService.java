package service;

import dto.UserRegistrationDto;
import model.User;

public interface UserService {

    User save(UserRegistrationDto registration);

}
