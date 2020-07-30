package com.euvic.carrental.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApi {

    @RequestMapping(method = RequestMethod.GET,value = "/all")
    public String sayHelloAll(){
        return "Hello everybody";
    }

    @RequestMapping(method = RequestMethod.GET,value = "/user")
    public String sayHelloUser(){
        return "Hello user";
    }

    @RequestMapping(method = RequestMethod.GET,value = "/admin")
    public String sayHelloAdmin(){
        return "Hello admin";
    }
}
