package com.euvic.carrental.controllers;

import com.euvic.carrental.model.User;
import com.euvic.carrental.responses.UserDTO;
import com.euvic.carrental.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/a")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<UserDTO> getAllUsers(){
        return userService.getAllDTOs();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/user")
    public Long addUserToDatabase(@RequestBody UserDTO userDTO){
        User user = userService.mapRestModel(null, userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.addEntityToDB(user);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/user/{login}")
    public Long updateDataBaseUser(@PathVariable String login, @RequestBody UserDTO newUserDTO){
        newUserDTO.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
        return userService.updateUserInDB(login, newUserDTO);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{login}")
    public Long setUserAsDeletedInDB(@PathVariable String login){
        return userService.setUserIsNotActive(login);
    }
}
