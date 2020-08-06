package com.euvic.carrental.services;

import com.euvic.carrental.model.Role;
import com.euvic.carrental.model.User;
import com.euvic.carrental.repositories.RoleRepository;
import com.euvic.carrental.repositories.UserRepository;
import com.euvic.carrental.responses.User.UserCreation;
import com.euvic.carrental.responses.User.UserDTO;
import com.euvic.carrental.responses.User.UserUpdate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    void whenUserDTO_And_UserCreation_Given_thenReturnUserEntity() {
        final User user = new User(null, "login1", "password1", "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", roleService.getEntityByRoleName("User"));
        final UserDTO userDTO = new UserDTO("login1", "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", roleService.getDTOByRoleName("User"));
        final UserCreation userCreation = new UserCreation("login1", bCryptPasswordEncoder.encode("password"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", roleService.getDTOByRoleName("User"));
        final User restModelToEntityUser = userService.mapRestModel(null, userDTO);
        final User updateModelToEntityUser = userService.mapCreationModel(null, userCreation);
        assertAll(() -> {
            assertEquals(restModelToEntityUser.getLogin(), user.getLogin());
            assertEquals(restModelToEntityUser.getEmail(), user.getEmail());
            assertEquals(restModelToEntityUser.getName(), user.getName());
            assertEquals(restModelToEntityUser.getSurname(), user.getSurname());
            assertEquals(restModelToEntityUser.getPhoneNumber(), user.getPhoneNumber());
            assertEquals(restModelToEntityUser.getIsActive(), user.getIsActive());
            assertEquals(restModelToEntityUser.getRole(), user.getRole());
            assertEquals(restModelToEntityUser.getId(), user.getId());


            assertEquals(updateModelToEntityUser.getLogin(), user.getLogin());
            assertEquals(updateModelToEntityUser.getEmail(), user.getEmail());
            assertEquals(updateModelToEntityUser.getName(), user.getName());
            assertEquals(updateModelToEntityUser.getSurname(), user.getSurname());
            assertEquals(updateModelToEntityUser.getPhoneNumber(), user.getPhoneNumber());
            assertEquals(updateModelToEntityUser.getIsActive(), user.getIsActive());
            assertEquals(updateModelToEntityUser.getRole(), user.getRole());
            assertEquals(updateModelToEntityUser.getId(), user.getId());
        });
    }

    @Test
    void shouldReturnDBUserEntity() {
        final User user = new User(null, "login1", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", roleService.getEntityByRoleName("User"));
        assertEquals(0, userRepository.count());
        userRepository.save(user);
        assertEquals(1, userRepository.count());
        final User serviceUser1 = userService.getEntityByLogin("login1");
        final User serviceUser2 = userService.getEntityByEmail("email@email.com");

        assertAll(() -> {
            assertEquals(user.getLogin(), serviceUser1.getLogin());
            assertEquals(user.getPassword(), serviceUser1.getPassword());
            assertEquals(user.getIsActive(), serviceUser1.getIsActive());
            assertEquals(user.getEmail(), serviceUser1.getEmail());
            assertEquals(user.getName(), serviceUser1.getName());
            assertEquals(user.getSurname(), serviceUser1.getSurname());
            assertEquals(user.getPhoneNumber(), serviceUser1.getPhoneNumber());
            assertEquals(user.getRole(), serviceUser1.getRole());
            assertNotEquals(null, serviceUser1.getId());


            assertEquals(user.getLogin(), serviceUser2.getLogin());
            assertEquals(user.getPassword(), serviceUser2.getPassword());
            assertEquals(user.getIsActive(), serviceUser1.getIsActive());
            assertEquals(user.getEmail(), serviceUser2.getEmail());
            assertEquals(user.getName(), serviceUser2.getName());
            assertEquals(user.getSurname(), serviceUser2.getSurname());
            assertEquals(user.getPhoneNumber(), serviceUser2.getPhoneNumber());
            assertEquals(user.getRole(), serviceUser2.getRole());
            assertNotEquals(null, serviceUser2.getId());
        });
    }

    @Test
    void shouldReturnDBUserDTO() {
        final User user = new User(null, "login1", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", roleService.getEntityByRoleName("User"));
        assertEquals(0, userRepository.count());
        userRepository.save(user);
        assertEquals(1, userRepository.count());

        final UserDTO serviceUserDTO1 = userService.getDTOByLogin("login1");
        final UserDTO serviceUserDTO2 = userService.getDTOByEmail("email@email.com");

        assertAll(() -> {
            assertEquals(user.getLogin(), serviceUserDTO1.getLogin());
            assertEquals(user.getEmail(), serviceUserDTO1.getEmail());
            assertEquals(user.getName(), serviceUserDTO1.getName());
            assertEquals(user.getSurname(), serviceUserDTO1.getSurname());
            assertEquals(user.getPhoneNumber(), serviceUserDTO1.getPhoneNumber());
            assertEquals(user.getRole().getName(), serviceUserDTO1.getRoleDTO().getName());


            assertEquals(user.getLogin(), serviceUserDTO2.getLogin());
            assertEquals(user.getEmail(), serviceUserDTO2.getEmail());
            assertEquals(user.getName(), serviceUserDTO2.getName());
            assertEquals(user.getSurname(), serviceUserDTO2.getSurname());
            assertEquals(user.getPhoneNumber(), serviceUserDTO2.getPhoneNumber());
            assertEquals(user.getRole().getName(), serviceUserDTO2.getRoleDTO().getName());
        });
    }

    @Test
    void shouldReturnAllDBUserDTO_And_ActiveUserDTOs() {
        final User user1 = new User(null, "login1", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", roleService.getEntityByRoleName("User"));
        final User user2 = new User(null, "login2", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", roleService.getEntityByRoleName("User"));
        final User user3 = new User(null, "login3", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", roleService.getEntityByRoleName("User"));

        assertEquals(0, userRepository.count());
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        assertEquals(3, userRepository.count());

        final List<UserDTO> allUserDTOList = userService.getAllDTOs();
        assertEquals(userRepository.count(), allUserDTOList.size());

        userService.setUserIsNotActive("login2");
        final List<UserDTO> activeUserDTOList = userService.getAllActiveUserDTOs();
        assertEquals(2, activeUserDTOList.size());
    }

    @Test
    void whenUserDTOGiven_shouldAddEntityUserToDB() {
        final User user = new User(null, "login1", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", roleService.getEntityByRoleName("User"));
        assertEquals(0, userRepository.count());
        userService.addEntityToDB(user);
        assertEquals(1, userRepository.count());
    }

    @Test
    void whenUserDTOGiven_shouldUpdateExistingDBUserExceptPassword() {
        final User user = new User(null, "login1", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", roleService.getEntityByRoleName("User"));
        final UserUpdate userUpdate = new UserUpdate("mail@email.com",
                "Wojcieh", "Waleszcyk", "700 122 110");

        assertEquals(0, userRepository.count());
        final Long userId = userService.addEntityToDB(user);
        assertEquals(1, userRepository.count());
        userService.updateUserInDB(user.getLogin(), userUpdate);
        final User updatedUser = userService.getEntityByLogin("login1");

        assertAll(() -> {
            assertEquals(userId, updatedUser.getId());
            assertEquals("login1", updatedUser.getLogin());
            assertEquals("mail@email.com", updatedUser.getEmail());
            assertEquals("Wojcieh", updatedUser.getName());
            assertEquals("Waleszcyk", updatedUser.getSurname());
            assertEquals("700 122 110", updatedUser.getPhoneNumber());
            assertEquals(true, updatedUser.getIsActive());
            assertEquals(roleService.getEntityByRoleName("User"), updatedUser.getRole());
        });
    }

    @Test
    void whenUserLoginGiven_shouldSetUserIsNotActive() {
        final User user = new User(null, "login1", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", roleService.getEntityByRoleName("User"));

        assertEquals(0, userRepository.count());
        userService.addEntityToDB(user);
        assertEquals(1, userRepository.count());
        assertTrue(user.getIsActive());
        userService.setUserIsNotActive(user.getLogin());
        final User updatedUser = userService.getEntityByLogin(user.getLogin());
        assertFalse(updatedUser.getIsActive());
    }

    @Test
    void shouldCheckIfUserInDBExists() {
        final User user1 = new User(null, "login1", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", roleService.getEntityByRoleName("User"));

        final User user2 = new User(null, "login2", bCryptPasswordEncoder.encode("password2"), "mail@email.com",
                "Wojcieh", "Waleszcyk", "700 120 110", roleService.getEntityByRoleName("User"));

        assertEquals(0, userRepository.count());
        userService.addEntityToDB(user1);
        assertEquals(1, userRepository.count());
        assertTrue(userService.checkIfUserWithLoginExists("login1"));
        assertFalse(userService.checkIfUserWithLoginExists("login2"));
    }

    @Test
    void whenNewPasswordGiven_shouldSetNewPasswordToUser() {
        final User user1 = new User(null, "login1", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", roleService.getEntityByRoleName("User"));

        final String password = "password1";
        final String newPassword = "newPassword";

        assertTrue(userService.checkPassword(password, user1.getPassword()));

        userService.changePassword(user1, newPassword);

        final User user2 = userService.getEntityByLogin("login1");

        assertTrue(userService.checkPassword(newPassword, user2.getPassword()));
    }

    @Test
    void whenNewEmailGiven_shouldSetNewEmailToUser() {
        final User user1 = new User(null, "login1", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700 100 110", roleService.getEntityByRoleName("User"));

        final String password = "password1";
        final String oldEmail = "email@email.com";
        final String newEmail = "sdsdsd@email.com";

        assertEquals(oldEmail, user1.getEmail());

        userService.changeEmail(user1, newEmail);

        final User user2 = userService.getEntityByLogin("login1");

        assertEquals(newEmail, user2.getEmail());
    }

    @Test
    void whenNewPhoneNumberGiven_shouldSetNewPhoneNumberToUser() {
        final User user1 = new User(null, "login1", bCryptPasswordEncoder.encode("password1"), "email@email.com",
                "Wojciech", "Waleszczyk", "700100110", roleService.getEntityByRoleName("User"));

        final String password = "password1";
        final String oldNumber = "700100110";
        final String newNumber = "800100110";

        assertEquals(oldNumber, user1.getPhoneNumber());

        userService.changePhoneNumber(user1, newNumber);

        final User user2 = userService.getEntityByLogin("login1");

        assertEquals(newNumber, user2.getPhoneNumber());
    }

    @Test
    void whenGoodFormatEmail_shouldReturnTrue() {
        final String goodEmail = "user@user.com";
        final String wrongEmail1 = "ddd...ds.pl";
        final String wrongEmail2 = "ddd..@@.ds.pl";

        assertTrue(userService.checkEmail(goodEmail));
        assertNotEquals(true, userService.checkEmail(wrongEmail1));
        assertNotEquals(true, userService.checkEmail(wrongEmail2));
    }

    @Test
    void whenGoodFormatPhoneNumber_shouldReturnTrue() {
        final String goodPhoneNumber = "508376153";
        final String wrongPhoneNumber1 = "344555777";
        final String wrongPhoneNumber2 = "32wr4343";

        assertTrue(userService.checkPhoneNumber(goodPhoneNumber));
        assertNotEquals(true, userService.checkPhoneNumber(wrongPhoneNumber1));
        assertNotEquals(true, userService.checkPhoneNumber(wrongPhoneNumber2));
    }

}
