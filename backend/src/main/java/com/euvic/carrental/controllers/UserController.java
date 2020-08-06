package com.euvic.carrental.controllers;

import com.euvic.carrental.model.User;
import com.euvic.carrental.responses.User.UserCration;
import com.euvic.carrental.responses.User.UserUpdate;
import com.euvic.carrental.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


//TODO tylko zwraca userów, jest 1 admin i nie mozna go edytować
//TODO admin nie usuwa samego siebie
//TODO walidacja

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
    public ResponseEntity getAllActiveUsers(){
        return ResponseEntity.ok(userService.getAllActiveUserDTOs());
    }

    @RequestMapping(method = RequestMethod.POST,value = "/user")
    public ResponseEntity addUserToDatabase(@RequestBody UserCration userCration){
        if(userService.checkIfUserWithLoginExists(userCration.getLogin())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Existing login given.");
        }

        User user = userService.mapCreationModel(null, userCration);
        user.setPassword(passwordEncoder.encode(userCration.getPassword()));
        return ResponseEntity.ok(userService.addEntityToDB(user));
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/user/{login}")
    public ResponseEntity updateDataBaseUser(@PathVariable String login, @RequestBody UserUpdate userUpdate){
        return ResponseEntity.ok(userService.updateUserInDB(login, userUpdate));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{login}")
    public ResponseEntity setUserAsDeletedInDB(@PathVariable String login){
        return ResponseEntity.ok(userService.setUserIsNotActive(login));
    }
}