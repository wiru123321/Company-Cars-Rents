package com.euvic.carrental.services;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Role;
import com.euvic.carrental.model.User;
import com.euvic.carrental.repositories.RoleRepository;
import com.euvic.carrental.repositories.UserRepository;
import com.euvic.carrental.responses.UserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    void setUp() {
        final Role role1 = new Role(null, "Admin");
        final Role role2 = new Role(null, "User");
        final Role role3 = new Role(null, "Manager");

        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    void whenUserDTOGiven_thenReturnUserEntity() {
        final User user = new User(null, "login1", "password1", "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", true, roleService.getEntityByRoleName("User"));
        final UserDTO userDTO = new UserDTO("login1", "password1", "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", true, roleService.getDTOByRoleName("User"));
        final User restModelToEntityUser = userService.mapRestModel(null, userDTO);
        assertAll(() -> {
            assertEquals(restModelToEntityUser.getLogin(), user.getLogin());
            assertEquals(restModelToEntityUser.getPassword(), user.getPassword());
            assertEquals(restModelToEntityUser.getEmail(), user.getEmail());
            assertEquals(restModelToEntityUser.getName(), user.getName());
            assertEquals(restModelToEntityUser.getSurname(), user.getSurname());
            assertEquals(restModelToEntityUser.getPhoneNumber(), user.getPhoneNumber());
            assertEquals(restModelToEntityUser.getIsActive(), user.getIsActive());
            assertEquals(restModelToEntityUser.getRole(), user.getRole());
            assertEquals(restModelToEntityUser.getId(), user.getId());
        });
    }

    @Test
    void shouldReturnDBUserEntity() {
        final User user = new User(null, "login1", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", true, roleService.getEntityByRoleName("User"));
        assertEquals(0, userRepository.count());
        userRepository.save(user);
        assertEquals(1, userRepository.count());
        final User serviceUser1 = userService.getEntityByLogin("login1");
        final User serviceUser2 = userService.getEntityByEmail("email@email.com");

        assertAll(() -> {
            assertEquals(user.getLogin(), serviceUser1.getLogin());
            assertEquals(user.getPassword(), serviceUser1.getPassword());
            assertEquals(user.getEmail(), serviceUser1.getEmail());
            assertEquals(user.getName(), serviceUser1.getName());
            assertEquals(user.getSurname(), serviceUser1.getSurname());
            assertEquals(user.getPhoneNumber(), serviceUser1.getPhoneNumber());
            assertEquals(user.getIsActive(), serviceUser1.getIsActive());
            assertEquals(user.getRole(), serviceUser1.getRole());
            assertNotEquals(null, serviceUser1.getId());


            assertEquals(user.getLogin(), serviceUser2.getLogin());
            assertEquals(user.getPassword(), serviceUser2.getPassword());
            assertEquals(user.getEmail(), serviceUser2.getEmail());
            assertEquals(user.getName(), serviceUser2.getName());
            assertEquals(user.getSurname(), serviceUser2.getSurname());
            assertEquals(user.getPhoneNumber(), serviceUser2.getPhoneNumber());
            assertEquals(user.getIsActive(), serviceUser2.getIsActive());
            assertEquals(user.getRole(), serviceUser2.getRole());
            assertNotEquals(null, serviceUser2.getId());
        });
    }

    @Test
    void shouldReturnDBUserDTO() {
        final User user = new User(null, "login1", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", true, roleService.getEntityByRoleName("User"));
        assertEquals(0, userRepository.count());
        userRepository.save(user);
        assertEquals(1, userRepository.count());

        final UserDTO serviceUserDTO1 = userService.getDTOByLogin("login1");
        final UserDTO serviceUserDTO2 = userService.getDTOByEmail("email@email.com");

        assertAll(() -> {
            assertEquals(user.getLogin(), serviceUserDTO1.getLogin());
            assertEquals(user.getPassword(), serviceUserDTO1.getPassword());
            assertEquals(user.getEmail(), serviceUserDTO1.getEmail());
            assertEquals(user.getName(), serviceUserDTO1.getName());
            assertEquals(user.getSurname(), serviceUserDTO1.getSurname());
            assertEquals(user.getPhoneNumber(), serviceUserDTO1.getPhoneNumber());
            assertEquals(user.getIsActive(), serviceUserDTO1.getIsActive());
            assertEquals(user.getRole().getName(), serviceUserDTO1.getRoleDTO().getName());


            assertEquals(user.getLogin(), serviceUserDTO2.getLogin());
            assertEquals(user.getPassword(), serviceUserDTO2.getPassword());
            assertEquals(user.getEmail(), serviceUserDTO2.getEmail());
            assertEquals(user.getName(), serviceUserDTO2.getName());
            assertEquals(user.getSurname(), serviceUserDTO2.getSurname());
            assertEquals(user.getPhoneNumber(), serviceUserDTO2.getPhoneNumber());
            assertEquals(user.getIsActive(), serviceUserDTO2.getIsActive());
            assertEquals(user.getRole().getName(), serviceUserDTO2.getRoleDTO().getName());
        });
    }

    @Test
    void shouldReturnAllDBUserDTO() {
        final User user1 = new User(null, "login1", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", true, roleService.getEntityByRoleName("User"));
        final User user2 = new User(null, "login2", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", true, roleService.getEntityByRoleName("User"));
        final User user3 = new User(null, "login3", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", true, roleService.getEntityByRoleName("User"));

        assertEquals(0, userRepository.count());
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        assertEquals(3, userRepository.count());

        final List<UserDTO> userDTOList = userService.getAllDTOs();

        assertEquals(userRepository.count(), userDTOList.size());
    }

    @Test
    void whenUserDTOGiven_shouldAddEntityUserToDB() {
        final User user = new User(null, "login1", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", true, roleService.getEntityByRoleName("User"));
        assertEquals(0, userRepository.count());
        userService.addEntityToDB(user);
        assertEquals(1, userRepository.count());
    }

    @Test
    void whenUserDTOGiven_shouldUpdateExistingDBUser(){
        final User user = new User(null, "login1", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", true, roleService.getEntityByRoleName("User"));
        final UserDTO userDTO = new UserDTO("login2", bCryptPasswordEncoder.encode("password2"), "mail@email.com",
                "Wojcieh", "Waleszcyk", "700 122 110", true, roleService.getDTOByRoleName("User"));

        assertEquals(0, userRepository.count());
        Long userId = userService.addEntityToDB(user);
        assertEquals(1, userRepository.count());
        userService.updateUserInDB(user.getLogin(), userDTO);
        final User updatedUser = userService.getEntityByLogin("login2");

        assertAll(()->{
            assertEquals(userId, updatedUser.getId());
            assertEquals("login2", updatedUser.getLogin());
            assertEquals(bCryptPasswordEncoder.encode("password2"), updatedUser.getPassword());
            assertEquals("mail@email.com", updatedUser.getEmail());
            assertEquals("Wojcieh", updatedUser.getName());
            assertEquals("Waleszcyk", updatedUser.getSurname());
            assertEquals("700 122 110", updatedUser.getPhoneNumber());
            assertEquals(true, updatedUser.getIsActive());
            assertEquals(roleService.getEntityByRoleName("User"), updatedUser.getRole());
        });
    }

    @Test
    void whenUserLoginGiven_shouldSetUserIsNotActive(){
        final User user = new User(null, "login1", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", true, roleService.getEntityByRoleName("User"));

        assertEquals(0, userRepository.count());
        userService.addEntityToDB(user);
        assertEquals(1, userRepository.count());
        assertTrue(user.getIsActive());
        userService.setUserIsNotActive(user.getLogin());
        User updatedUser = userService.getEntityByLogin(user.getLogin());
        assertFalse(updatedUser.getIsActive());
    }
}
