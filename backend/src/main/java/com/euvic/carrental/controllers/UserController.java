package com.euvic.carrental.controllers;

import com.euvic.carrental.model.User;
import com.euvic.carrental.responses.User.UserCreation;
import com.euvic.carrental.responses.User.UserDTO;
import com.euvic.carrental.responses.User.UserUpdate;
import com.euvic.carrental.services.RentService;
import com.euvic.carrental.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/a")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RentService rentService;

    public UserController(final UserService userService, final PasswordEncoder passwordEncoder, final RentService rentService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.rentService = rentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ResponseEntity<?> getAllActiveUsersExceptCurrent() {
        final List<UserDTO> userDTOList = userService.getAllActiveUserDTOs();
        userDTOList.remove(userService.getDTOByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
        return ResponseEntity.ok(userDTOList);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public ResponseEntity<?> addUserToDatabase(@RequestBody final UserCreation userCreation) {
        if (userService.checkIfUserWithLoginExists(userCreation.getLogin())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Existing login given.");
        }

        final User user = userService.mapCreationModel(null, userCreation);
        user.setPassword(passwordEncoder.encode(userCreation.getPassword()));
        return ResponseEntity.ok(userService.addEntityToDB(user));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user/{login}")
    public ResponseEntity<?> updateDataBaseUser(@PathVariable final String login, @RequestBody final UserUpdate userUpdate) {
        if (!userService.checkIfUserWithLoginExists(login)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is no user with passed login.");
        }
        return ResponseEntity.ok(userService.updateUserInDB(login, userUpdate));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{login}")
    public ResponseEntity<?> setUserAsDeletedInDB(@PathVariable final String login) {
        if (!userService.checkIfUserWithLoginExists(login)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is no user with passed login.");
        }
        rentService.deleteRentsByUser(userService.getEntityByLogin(login));

        return ResponseEntity.ok(userService.setUserIsNotActive(login));
    }
}