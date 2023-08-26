package springrestapii.demo.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import springrestapii.demo.dto.UserDto;
import springrestapii.demo.entity.User;
import springrestapii.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        //converting UserDto to JPA entity and then pass as argument to repository.
        User user = modelMapper.map(userDto, User.class);

        User savedUser = userRepository.save(user);

        UserDto savedUserDto = modelMapper.map(savedUser,UserDto.class);

        return savedUserDto;
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        User usersList = user.get();

        return modelMapper.map(usersList,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map((user -> modelMapper.map(user,UserDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return modelMapper.map(userRepository.save(existingUser),UserDto.class);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
