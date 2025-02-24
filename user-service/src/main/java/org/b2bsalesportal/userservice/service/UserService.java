package org.b2bsalesportal.userservice.service;

import org.b2bsalesportal.userservice.dto.UserDTO;
import org.b2bsalesportal.userservice.dto.requests.UserLoginDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    String saveUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    String verify(UserLoginDTO userLoginDTO);
}
