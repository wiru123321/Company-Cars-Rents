package com.euvic.carrental.controllers;

import com.euvic.carrental.model.User;
import com.euvic.carrental.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@RestController
public class LoginApi{

    private final UserService userService;

    public LoginApi(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/logIn")
    public String get(@RequestBody User userLoginInfo){


/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(User.builder()
                .username("username")
                .password(getPasswordEncoder().encode("password"))
                .roles("Admin"));
    }
*/

        long currentTimeMillis = System.currentTimeMillis();
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("F~ak%+G=VJb=sjr");
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        User user = userService.getEntityByLogin(userLoginInfo.getLogin());
        if(user != null && (userLoginInfo.getPassword() != user.getPassword() && user.getIsActive())){
                return Jwts.builder()
                        .setSubject(user.getLogin())
                        .claim("name", user.getName())
                        .claim("role", "ROLE_" + user.getRole().getName())
                        .setIssuedAt(new Date(currentTimeMillis))
                        .setExpiration(new Date(currentTimeMillis + 900000))
                        .signWith(signatureAlgorithm, signingKey)
                        .compact();
        }

        return Jwts.builder()
                .setSubject(userLoginInfo.getLogin())
                .claim("name", "unknown")
                .claim("role", "unknown")
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 20000))
                .signWith(signatureAlgorithm, signingKey)
                .compact();
    }
}
