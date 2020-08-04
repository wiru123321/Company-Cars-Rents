package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.User;
import com.euvic.carrental.responses.UserDTO;

import java.util.List;

public interface UserServiceInterface {
    User mapRestModel(Long id, UserDTO userDTO);

    User getEntityByLogin(String name);

    User getEntityByEmail(String email);

    UserDTO getDTOByEmail(String email);

    Long addEntityToDB(User user);

    UserDTO getDTOByLogin(String login);

    List<UserDTO> getAllDTOs();

    boolean checkCrudentials(String given, String actual);
}
