package com.euvic.carrental.services;

import com.euvic.carrental.model.User;
import com.euvic.carrental.repositories.UserRepository;
import com.euvic.carrental.responses.User.UserCreation;
import com.euvic.carrental.responses.User.UserDTO;
import com.euvic.carrental.responses.User.UserUpdate;
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

    @Override
    public User mapCreationModel(final Long id, final UserCreation userCreation) {
        return new User(id, userCreation, roleService.getEntityByRoleName(userCreation.getRoleDTO().getName()));
    }

    @Override
    public User getEntityByLogin(final String login) {
        return userRepository.findByLogin(login);
    }

    @Override   //TODO test it
    public User getEntityByLoginAndisActive(final String login, final Boolean isActive) {
        return userRepository.findByLoginAndIsActive(login, isActive);
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
        final ArrayList<User> userArrayList = new ArrayList<>(userRepository.findAllByIsActive(true));
        return this.mapRestList(userArrayList);
    }

    @Override //NO PASSWORD CHANGE
    public Long updateUserInDB(final String login, final UserUpdate userUpdate) {
        final User oldUser = this.getEntityByLoginAndisActive(login, true);
        oldUser.setName(userUpdate.getName());
        oldUser.setSurname(userUpdate.getSurname());
        oldUser.setEmail(userUpdate.getEmail());
        oldUser.setPhoneNumber(userUpdate.getPhoneNumber());
        return userRepository.save(oldUser).getId();
    }

    @Override
    public Long setUserIsNotActive(final String login) {
        final User user = this.getEntityByLogin(login);
        user.setIsActive(false);
        return userRepository.save(user).getId();
    }

    @Override
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

    @Override
    public boolean changePassword(final User user, final String password) {
        final String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[_.@#$%^&+=])(?=\\S+$).{8,}";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(password);
        System.out.println(matcher.matches());
        System.out.println(password);
        if (matcher.matches()) {
            user.setPassword(bCryptPasswordEncoder.encode(password));
            userRepository.save(user);
        }
        return matcher.matches();
    }

    @Override
    public void changeEmail(final User user, final String email) {
        user.setEmail(email);
        userRepository.save(user);
    }

    @Override
    public void changePhoneNumber(final User user, final String phoneNumber) {
        user.setPhoneNumber(phoneNumber);
        userRepository.save(user);
    }

    @Override
    public boolean checkPassword(final String given, final String actual) {
        return bCryptPasswordEncoder.matches(given, actual);
    }

    @Override
    public boolean checkEmail(final String email) {
        final String regex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean checkPhoneNumber(final String phoneNumber) {

        final String regex = "[5-9][0-9]{8}";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
