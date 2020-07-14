package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "roles")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String roleName;

    @OneToMany(mappedBy = "role",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<User> users;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
