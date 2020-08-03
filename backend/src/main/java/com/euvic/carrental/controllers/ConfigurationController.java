package com.euvic.carrental.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigurationController {
    @RequestMapping(value = "/myerror")
    public String myerror() {
        return "<center><h1>Something went wrong</h1></center>";
    }
}
