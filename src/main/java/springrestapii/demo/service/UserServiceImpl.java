package springrestapii.demo.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import springrestapii.demo.dto.UserDto;
import springrestapii.demo.entity.User;
import springrestapii.demo.exception.EmailException;
import springrestapii.demo.exception.ResourceNotFound;
import springrestapii.demo.mapper.UserMapper;
import springrestapii.demo.mapper.UserMapping;
import springrestapii.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// We have ModelMapper library and MapStruct library,
// but we have used MapStruct library to automatically map jpa objects to dto and vice-versa.

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        //converting UserDto to JPA entity and then pass as argument to repository.
        //User user = modelMapper.map(userDto, User.class);

        //find user by email
        Optional<User> isFound = userRepository.findByEmail(userDto.getEmail());

        //if user found with email, then throw exception and block user to create user only with unique email.
        if (isFound.isPresent()) {
            throw new EmailException("Email already in use");
        }

        User user = UserMapping.MAPPER.convertToJpa(userDto);

        User savedUser = userRepository.save(user);

        //UserDto savedUserDto = modelMapper.map(savedUser,UserDto.class);

        UserDto savedUserDto = UserMapping.MAPPER.convertToDto(savedUser);

        return savedUserDto;
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("User", "id", id)
        );

        //return modelMapper.map(usersList,UserDto.class);
        return UserMapping.MAPPER.convertToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();


    /*
        return users.stream().map((user -> modelMapper.map(user,UserDto.class)))
                .collect(Collectors.toList());
    */
        return users.stream().map((user -> UserMapping.MAPPER.convertToDto(user)))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFound("User", "id", user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        //return modelMapper.map(userRepository.save(existingUser),UserDto.class);
        return UserMapping.MAPPER.convertToDto(existingUser);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("user", "id", id)
        );
        userRepository.deleteById(id);
    }
}
