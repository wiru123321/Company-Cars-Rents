package com.euvic.carrental.services;

import com.euvic.carrental.model.User;
import com.euvic.carrental.repositories.UserRepository;
import com.euvic.carrental.responses.UserDTO;
import com.euvic.carrental.services.interfaces.UserServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    final UserRepository userRepository;
    final RoleService roleService;

    public UserService(final UserRepository userRepository, final RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public User mapRestModel(final UserDTO userDTO) {
        return new User(userDTO, roleService.getEntityByRoleName(userDTO.getRoleDTO().getName()));
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
    public void add(final UserDTO userDTO) {
        userRepository.save(this.mapRestModel(userDTO));
    }

    @Override
    public List<UserDTO> getAll() {
        final ArrayList<User> userArrayList = new ArrayList<>();
        userRepository.findAll().forEach(userArrayList::add);

        final ArrayList<UserDTO> userDTOArrayList = new ArrayList<>();
        userArrayList.stream().forEach((user) -> {
            final UserDTO userDTO = new UserDTO(user, roleService.getDTOByRoleName(user.getRole().getName()));
            userDTOArrayList.add(userDTO);
        });

        return userDTOArrayList;
    }
}
