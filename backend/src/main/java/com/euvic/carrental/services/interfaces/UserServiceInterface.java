package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.User;
import com.euvic.carrental.responses.User.UserCreation;
import com.euvic.carrental.responses.User.UserDTO;
import com.euvic.carrental.responses.User.UserUpdate;

import java.util.List;

public interface UserServiceInterface {
    User mapRestModel(Long id, UserDTO userDTO);

    User getEntityByLogin(String name);

    User getEntityByEmail(String email);

    UserDTO getDTOByEmail(String email);

    Long addEntityToDB(User user);

    UserDTO getDTOByLogin(String login);

    List<UserDTO> getAllDTOs();

    Long updateUserInDB(String login, UserUpdate userUpdate);

    Long setUserIsNotActive(String login);

    User mapCreationModel(Long id, UserCreation userCreation);

    List<UserDTO> getAllActiveUserDTOs();

    Boolean checkIfUserWithLoginExists(String login);

    boolean checkPassword(String given, String actual);
}
