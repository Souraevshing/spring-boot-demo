package springrestapii.demo.mapper;

import springrestapii.demo.dto.UserDto;
import springrestapii.demo.entity.User;

//UserMapper class is used to make conversion of JPA to User Dto and vice-versa.
//creating static method to convert and reduce same code again and again.

//in this project, we are not using this class to convert rather using 3rd party library ModelMapper and MapStruct

/**
 * The goal of ModelMapper is to make object mapping easy,
 * by automatically determining how one object model maps to another,
 * based on conventions, in the same way that a human would - while providing a simple,
 * refactoring-safe API for handling specific use cases. */

/**
 * MapStruct is a code generator tool that greatly simplifies the implementation of mappings between Java bean types
 * based on a convention over configuration approach.
 */

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
