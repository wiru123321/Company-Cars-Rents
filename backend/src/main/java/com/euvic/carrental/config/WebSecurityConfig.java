package com.euvic.carrental.config;

import com.euvic.carrental.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/logIn", "/u/**").permitAll()
                .antMatchers("/e/**").hasAnyRole("EMPLOYEE", "ADMIN")
                .antMatchers("/a/**").hasRole("ADMIN")
                .and()
                .addFilter(new JwtFilter(authenticationManager()));
    }



    @Bean
    public PasswordEncoder getPasswordEncoder() {
        final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }
}
