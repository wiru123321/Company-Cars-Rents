package com.euvic.carrental.controllers;

import com.euvic.carrental.model.User;
import com.euvic.carrental.responses.User.EditAccount;
import com.euvic.carrental.responses.User.UserInfoDTO;
import com.euvic.carrental.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/e")
public class AccountController {

    private final UserService userService;

    @Autowired
    public AccountController(final UserService userService) {
        this.userService = userService;
    }

    //GETs

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String sayHelloEmployee() {
        return "Hello employee";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/info")
    ResponseEntity<?> returnEmployee() {
        final UserInfoDTO userInfoDTO = new UserInfoDTO(userService.getDTOByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
        return ResponseEntity.status(200).body(userInfoDTO);
    }

    //PUTs

    @RequestMapping(method = RequestMethod.PUT, value = "/modify/password")
    ResponseEntity<?> updatePassword(@RequestBody final EditAccount editAccount) {

        final User user = userService.getEntityByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        if (editAccount.getPassword().equals("")) {
            return ResponseEntity.ok(editAccount);
        }

        final int responseCode;

        if (userService.checkPassword(editAccount.getPassword(), user.getPassword())) {
            if (userService.changePassword(user, editAccount.getNewPassword())) {
                responseCode = 200;
            } else {
                responseCode = 400;
            }
        } else {
            responseCode = 406;
        }

        editAccount.setPassword("");
        editAccount.setNewPassword("");
        return ResponseEntity.status(responseCode).body(editAccount);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/modify/email")
    ResponseEntity<?> updateEmail(@RequestBody final EditAccount editAccount) {

        final User user = userService.getEntityByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        if (editAccount.getPassword().equals("")) {
            return ResponseEntity.ok(editAccount);
        }

        final int responseCode;

        if (userService.checkPassword(editAccount.getPassword(), user.getPassword())) {
            if (userService.checkEmail(editAccount.getNewEmail())) {
                userService.changeEmail(user, editAccount.getNewEmail());
                responseCode = 200;
            } else {
                responseCode = 400;
            }

        } else {
            responseCode = 406;
        }
        editAccount.setPassword("");
        editAccount.setNewPassword("");
        return ResponseEntity.status(responseCode).body(editAccount);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/modify/phone")
    ResponseEntity<?> updatePhoneNumber(@RequestBody final EditAccount editAccount) {

        final User user = userService.getEntityByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        if (editAccount.getPassword().equals("")) {
            return ResponseEntity.ok(editAccount);
        }

        final int responseCode;

        if (userService.checkPassword(editAccount.getPassword(), user.getPassword())) {
            if (userService.checkPhoneNumber(editAccount.getNewPhoneNumber())) {
                userService.changePhoneNumber(user, editAccount.getNewPhoneNumber());
                responseCode = 200;
            } else {
                responseCode = 400;
            }
        } else {
            responseCode = 406;
        }
        editAccount.setPassword("");
        editAccount.setNewPassword("");
        return ResponseEntity.status(responseCode).body(editAccount);
    }
}
