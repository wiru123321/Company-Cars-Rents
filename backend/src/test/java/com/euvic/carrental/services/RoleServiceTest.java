package com.euvic.carrental.services;

import com.euvic.carrental.model.Role;
import com.euvic.carrental.repositories.RoleRepository;
import com.euvic.carrental.responses.RoleDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("h2")
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @AfterEach
    void tearDown(){
        roleRepository.deleteAll();
    }

    @Test
    void whenRoleGiven_thenReturnRoleDTO(){
        final Role role = new Role(null,"Admin");
        final RoleDTO roleDTO = new RoleDTO("Admin");
        assertAll(() -> {
            assertEquals(roleService.mapRestModel(roleDTO).getName(), role.getName());
            assertEquals(roleService.mapRestModel(roleDTO).getId(), role.getId());
        });
    }

    @Test
    void returnDBRole(){
        final Role role = new Role(null, "Admin");
        assertEquals(0, roleRepository.count());
        roleRepository.save(role);
        assertEquals(1, roleRepository.count());

        RoleDTO serviceRoleDTO = roleService.getByName("Admin");

        assertEquals(role.getName(), serviceRoleDTO.getName());
    }

    @Test
    void returnAllDBRoles(){
        final Role role1 = new Role(null, "Admin");
        final Role role2 = new Role(null, "User");
        final Role role3 = new Role(null, "NoOne");
        assertEquals(0, roleRepository.count());
        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
        assertEquals(3, roleRepository.count());

        List<RoleDTO> roleDTOList = roleService.getAll();

        assertEquals(roleRepository.count(), roleDTOList.size());
    }
}
