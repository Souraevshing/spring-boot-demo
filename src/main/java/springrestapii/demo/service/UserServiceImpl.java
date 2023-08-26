package springrestapii.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springrestapii.demo.dto.UserDto;
import springrestapii.demo.entity.User;
import springrestapii.demo.mapper.UserMapper;
import springrestapii.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        //converting UserDto to JPA entity and then pass as argument to repository.
        User user = UserMapper.convertToJpa(userDto);

        User savedUser = userRepository.save(user);

        return UserMapper.convertToDto(savedUser);
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        User usersList = user.get();

        return UserMapper.convertToDto(usersList);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map((UserMapper::convertToDto))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return UserMapper.convertToDto(userRepository.save(existingUser));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
