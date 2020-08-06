package com.euvic.carrental.services;

import com.euvic.carrental.model.User;
import com.euvic.carrental.repositories.UserRepository;
import com.euvic.carrental.responses.User.UserCration;
import com.euvic.carrental.responses.User.UserDTO;
import com.euvic.carrental.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UserService implements UserServiceInterface {

    final UserRepository userRepository;
    final RoleService roleService;
    final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(final UserRepository userRepository, final RoleService roleService, final PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override //NO PASSWORD MAPPING
    public User mapRestModel(final Long id, final UserDTO userDTO) {
        return new User(id, userDTO, roleService.getEntityByRoleName(userDTO.getRoleDTO().getName()));
    }

    @Override //TODO tests
    public User mapCreationModel(final Long id, final UserCration userCration) {
        return new User(id, userCration, roleService.getEntityByRoleName(userCration.getRoleDTO().getName()));
    }

    @Override
    public User getEntityByLogin(final String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public UserDTO getDTOByLogin(final String login) {
        final User user = userRepository.findByLogin(login);
        return new UserDTO(user, roleService.getDTOByRoleName(user.getRole().getName()));
    }

    @Override
    public User getEntityByEmail(final String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public UserDTO getDTOByEmail(final String email) {
        final User user = userRepository.findByEmail(email);
        return new UserDTO(user, roleService.getDTOByRoleName(user.getRole().getName()));
    }

    @Override
    public Long addEntityToDB(final User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public List<UserDTO> getAllDTOs() {
        final ArrayList<User> userArrayList = new ArrayList<>();
        userRepository.findAll().forEach(userArrayList::add);
        return this.mapRestList(userArrayList);
    }

    @Override
    public List<UserDTO> getAllActiveUserDTOs() {
        final ArrayList<User> userArrayList = new ArrayList<>();
        userArrayList.addAll(userRepository.findAllByIsActive(true));
        return this.mapRestList(userArrayList);
    }

    @Override //NO PASSWORD CHANGE
    public Long updateUserInDB(final String login, final UserDTO newUserDTO) {
        final User oldUser = this.getEntityByLogin(login);
        final User newUser = this.mapRestModel(oldUser.getId(), newUserDTO);
        newUser.setPassword(oldUser.getPassword());
        return userRepository.save(newUser).getId();
    }

    @Override
    public Long setUserIsNotActive(final String login) {
        final User user = this.getEntityByLogin(login);
        user.setIsActive(false);
        return userRepository.save(user).getId();
    }

    @Override //test it TODO
    public Boolean checkIfUserWithLoginExists(final String login) {
        return userRepository.existsByLogin(login);
    }

    private List<UserDTO> mapRestList(final List<User> userArrayList) {
        final ArrayList<UserDTO> userDTOArrayList = new ArrayList<>();
        userArrayList.forEach((user) -> {
            final UserDTO userDTO = new UserDTO(user, roleService.getDTOByRoleName(user.getRole().getName()));
            userDTOArrayList.add(userDTO);
        });
        return userDTOArrayList;
    }

    //test it TODO
    public void changePassword(final User user, final String password) {
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }

    //test it TODO
    public void changeEmail(final User user, final String email) {
        user.setEmail(email);
        userRepository.save(user);
    }

    //test it TODO
    public void changePhoneNumber(final User user, final String phoneNumber) {
        user.setPhoneNumber(phoneNumber);
        userRepository.save(user);
    }

    //test it TODO
    public boolean checkPassword(final String given, final String actual) {
        return bCryptPasswordEncoder.matches(given, actual);
    }

    //test it TODO
    public boolean checkEmail(final String email) {
        final String regex = "^(.+)@(.+)$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //test it TODO
    public boolean checkPhoneNumber(final String phoneNumber) {

        final String regex = "[5-9][0-9]{8}";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
