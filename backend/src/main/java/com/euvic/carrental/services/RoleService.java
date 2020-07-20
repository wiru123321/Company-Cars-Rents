package com.euvic.carrental.services;

import com.euvic.carrental.model.Role;
import com.euvic.carrental.repositories.RoleRepository;
import com.euvic.carrental.responses.RoleDTO;
import com.euvic.carrental.services.interfaces.RoleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService implements RoleServiceInterface {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDTO getByName(String name) {
        Role role = roleRepository.findByName(name);
        return new RoleDTO(role.getName());
    }

    @Override
    public Role mapRestModel(RoleDTO model) {
        return new Role(null, model.getName());
    }

    @Override
    public List<RoleDTO> getAll() {
        ArrayList<Role> roleList = new ArrayList<>();
        roleRepository.findAll().forEach(roleList::add);

        return roleList.stream().map(RoleDTO::new).collect(Collectors.toList());
    }
}
