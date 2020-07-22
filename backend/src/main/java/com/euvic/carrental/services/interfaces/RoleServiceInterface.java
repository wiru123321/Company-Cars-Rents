package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Role;
import com.euvic.carrental.responses.RoleDTO;

import java.util.List;

public interface RoleServiceInterface {
    Role mapRestModel(final RoleDTO model);
    RoleDTO getDTOByName(String name);
    List<RoleDTO> getAll();
}
