package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.User;
import com.euvic.carrental.responses.User.UserCreation;
import com.euvic.carrental.responses.User.UserDTO;
import com.euvic.carrental.responses.User.UserUpdate;

import java.util.List;

public interface UserServiceInterface {
    Long updateUserInDB(String login, UserUpdate userUpdate);

    Long addEntityToDB(User user);

    Long setUserIsNotActive(String login);

    Boolean checkIfUserWithLoginExists(String login);

    boolean checkPassword(String given, String actual);

    boolean changePassword(User user, String password);

    boolean checkEmail(String email);

    boolean checkPhoneNumber(String phoneNumber);

    void changeEmail(User user, String email);

    void changePhoneNumber(User user, String phoneNumber);

    User mapRestModel(Long id, UserDTO userDTO);

    User getEntityByLogin(String name);

    User getEntityByEmail(String email);

    User mapCreationModel(Long id, UserCreation userCreation);

    User getEntityByLoginAndisActive(final String login, final Boolean isActive);

    UserDTO getDTOByEmail(String email);

    UserDTO getDTOByLogin(String login);

    List<UserDTO> getAllDTOs();

    List<UserDTO> getAllActiveUserDTOs();
}
