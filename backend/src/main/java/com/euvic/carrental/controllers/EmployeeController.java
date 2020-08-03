package com.euvic.carrental.controllers;

import com.euvic.carrental.responses.UserInfoDTO;
import com.euvic.carrental.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/e")
@PreAuthorize("hasRole('Employee')")
public class EmployeeController {

    private final UserService userService;

    public EmployeeController(final UserService userService) {
        this.userService = userService;
    }

    //GETs

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String sayHelloEmployee() {
        return "Hello employee";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/info")
    ResponseEntity<?> returnEmployee() {
        return ResponseEntity.ok(new UserInfoDTO(userService.getDTOByLogin(SecurityContextHolder.getContext().getAuthentication().getName())));
    }


    //PUTs
}
