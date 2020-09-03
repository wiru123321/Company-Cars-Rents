package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Role;
import com.euvic.carrental.responses.RoleDTO;

import java.util.List;

public interface RoleServiceInterface {
    Role mapRestModel(Long id, RoleDTO model);

    Role getEntityByRoleName(String roleName);

    RoleDTO getDTOByRoleName(String name);

    List<RoleDTO> getAllDTOs();
}
