package org.b2bsalesportal.userservice.service.ServiceIMPL;

import org.b2bsalesportal.userservice.dto.UserDTO;
import org.b2bsalesportal.userservice.dto.requests.UserLoginDTO;
import org.b2bsalesportal.userservice.exception.AlreadyExistsException;
import org.b2bsalesportal.userservice.exception.NotFoundException;
import org.b2bsalesportal.userservice.model.User;
import org.b2bsalesportal.userservice.repository.UserRepository;
import org.b2bsalesportal.userservice.service.JWTService;
import org.b2bsalesportal.userservice.service.UserService;
import org.b2bsalesportal.userservice.util.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder =new BCryptPasswordEncoder(12);

    @Override
    public String saveUser(UserDTO userDTO) {

        if(userRepository.existsByEmail(userDTO.getEmail())){
            throw new AlreadyExistsException(userDTO.getEmail() +" Email already registered to the system");
        }
        User user=userMapper.dtoToEntity(userDTO);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        return user.getFirstName() + " saved successfully";
    }

    @Override
    public List<UserDTO> getAllUsers() {
        if(userRepository.count()==0){
            throw new NotFoundException("No users found");
        }else{
            List<User> users = userRepository.findAll();
            List<UserDTO> usersDTOs= userMapper.entityListToDTOList(users);
            return usersDTOs;
        }
    }

    @Override
    public String verify(UserLoginDTO userLoginDTO) {
        Authentication authentication=
                authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(
                        userLoginDTO.getEmail(),userLoginDTO.getPassword())));

        if(authentication.isAuthenticated()){
            return  jwtService.generateToken(userLoginDTO.getEmail());
        }
        return "Failure";
    }
}
