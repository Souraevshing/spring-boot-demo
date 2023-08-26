package springrestapii.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import springrestapii.demo.dto.UserDto;
import springrestapii.demo.entity.User;


// At runtime, MapStruct will automatically create implementation for both these methods.

/** Both Dto and Entity class should have same field names in order to map using MapStruct library,
 *  if any fields are having different names, then we will have to use @Mapping and pass source and target property like this
 * @Mapper(source = "email", target= "emailId") if any field in entity or dto class has emailId then we will explicitly have to do this.
 *
 */


@Mapper
public interface UserMapping {

    //Mappers class has method getMapper() to map objects from jpa to dto and vice-versa.
    UserMapping MAPPER = Mappers.getMapper(UserMapping.class);
    UserDto convertToDto(User user);

    User convertToJpa(UserDto userDto);

}
