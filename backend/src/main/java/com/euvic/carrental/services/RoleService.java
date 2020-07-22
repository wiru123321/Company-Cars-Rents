package com.euvic.carrental.services;

import com.euvic.carrental.model.Role;
import com.euvic.carrental.repositories.RoleRepository;
import com.euvic.carrental.responses.RoleDTO;
import com.euvic.carrental.services.interfaces.RoleServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService implements RoleServiceInterface {

    private final RoleRepository roleRepository;

    public RoleService(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role mapRestModel(final RoleDTO model) {
        return new Role(null, model.getName());
    }

    @Override
    public RoleDTO getDTOByRoleName(final String name) {
        final Role role = roleRepository.findByName(name);
        return new RoleDTO(role);
    }

    @Override
    public Role getEntityByRoleName(final String roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public List<RoleDTO> getAll() {
        final ArrayList<Role> roleList = new ArrayList<>();
        roleRepository.findAll().forEach(roleList::add);

        return roleList.stream().map(RoleDTO::new).collect(Collectors.toList());
    }
}
