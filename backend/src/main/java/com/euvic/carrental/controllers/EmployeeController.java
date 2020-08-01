package com.euvic.carrental.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/e")
public class EmployeeController {

    @RequestMapping(method = RequestMethod.GET,value = "/hello")
    public String sayHelloEmployee(){
        return "Hello employee";
    }
}
