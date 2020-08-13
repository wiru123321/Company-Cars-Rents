package com.euvic.carrental.responses;

import com.euvic.carrental.model.Role;
import lombok.Data;

@Data
public class RoleDTO {

    private String name;

    public RoleDTO(){

    }

    public RoleDTO(final Role entity){
        this.name = entity.getName();
    }

    public RoleDTO(final String name){
        this.name = name;
    }
}
