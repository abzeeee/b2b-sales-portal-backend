package org.b2bsalesportal.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.b2bsalesportal.userservice.dto.UserDTO;
import org.b2bsalesportal.userservice.dto.requests.UserLoginDTO;
import org.b2bsalesportal.userservice.service.UserService;
import org.b2bsalesportal.userservice.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/v1/user")
@Tag(name = "User Controller", description = "User management APIs")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/register")
    @Operation(summary = "Register User", description = "Register users")
    public ResponseEntity<StandardResponse> registerUser(@RequestBody UserDTO userDTO){
        String message= userService.saveUser(userDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",message),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping(path = "/get-all-users")
    @Operation(summary = "Get all users", description = "Get All users")
    public ResponseEntity<StandardResponse> getAllUsers(){
        List<UserDTO> users= userService.getAllUsers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",users),HttpStatus.OK
        );
    }

    @PostMapping(path="/login")
    @Operation(summary = "User Login", description = "User login to the system")
    public ResponseEntity<StandardResponse> login(@RequestBody UserLoginDTO userLoginDTO){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",userService.verify(userLoginDTO)),HttpStatus.ACCEPTED
        );
    }




}
