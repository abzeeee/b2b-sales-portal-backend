package org.b2bsalesportal.userservice.util.mappers;

import org.b2bsalesportal.userservice.dto.UserDTO;
import org.b2bsalesportal.userservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User dtoToEntity(UserDTO userDTO);

    UserDTO entityToDTO(User user);

    List<UserDTO> entityListToDTOList(List<User> users);
}
