package com.euvic.carrental.controllers;

import com.euvic.carrental.model.User;
import com.euvic.carrental.responses.User.UserCration;
import com.euvic.carrental.responses.User.UserDTO;
import com.euvic.carrental.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO NAPRAW TESTY PAPROTA !!!!!!!!!!!

//TODO not giving in rest api password back and isactive
//TODO checking while login that login isnt exist
//TODO tylko zwraca userów, jest 1 admin i nie mozna go edytować

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
    public List<UserDTO> getAllActiveUsers(){
        return userService.getAllActiveUserDTOs();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/user")
    public Long addUserToDatabase(@RequestBody UserCration userCration){
        User user = userService.mapCreationModel(null, userCration);
        user.setPassword(passwordEncoder.encode(userCration.getPassword()));
        return userService.addEntityToDB(user);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/user/{login}")
    public Long updateDataBaseUser(@PathVariable String login, @RequestBody UserDTO newUserDTO){
        return userService.updateUserInDB(login, newUserDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{login}")
    public Long setUserAsDeletedInDB(@PathVariable String login){
        return userService.setUserIsNotActive(login);
    }
}