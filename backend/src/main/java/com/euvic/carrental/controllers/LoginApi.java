package com.euvic.carrental.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class LoginApi {

    @RequestMapping(method = RequestMethod.POST, value = "/logIn")
    public String get(@RequestBody User user){

        //Tu dodac sprawdzanie czy uzytkownik znajduje sie w bazie danych
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(user.getLogin())
                .claim("roles", "user")
                .setIssuedAt(new Date(currentTimeMillis)) // od kiedy wazny
                .setExpiration(new Date(currentTimeMillis + 20000)) // do kiedy wazny
                .signWith(SignatureAlgorithm.HS512, user.getPassword()) // szyfrowane haslo
                .compact();

    }
}
