package com.euvic.carrental.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/u")
public class UnauthorizatedUserController {

    @RequestMapping(method = RequestMethod.GET,value = "/hello")
    public String sayHelloAll(){
        return "Hello whoever you are";
    }
}
