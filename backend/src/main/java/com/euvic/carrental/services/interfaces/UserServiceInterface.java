package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.User;
import com.euvic.carrental.responses.UserDTO;

import java.util.List;

public interface UserServiceInterface {
    User mapRestModel(final UserDTO userDTO);

    User getEntityByLogin(String name);

    User getEntityByEmail(String email);

    void add(UserDTO userDTO);

    UserDTO getDTOByLogin(final String login);

    List<UserDTO> getAll();
}
