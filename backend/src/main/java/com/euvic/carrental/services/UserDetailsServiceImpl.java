package com.euvic.carrental.services;

import com.euvic.carrental.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    protected PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    protected UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        final User user = userService.getEntityByLogin(userName);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), this.getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(final User user) {
        final Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));

        return authorities;
    }
}
