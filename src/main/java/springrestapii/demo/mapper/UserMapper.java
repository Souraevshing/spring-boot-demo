package springrestapii.demo.mapper;

import springrestapii.demo.dto.UserDto;
import springrestapii.demo.entity.User;

//UserMapper class is used to make conversion of JPA to User Dto and vice-versa.
//creating static method to convert and reduce same code again and again.
public class UserMapper {

    public static UserDto convertToDto(User user) {

        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );

    }

    public static User convertToJpa(UserDto userDto) {

        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );

    }

}
