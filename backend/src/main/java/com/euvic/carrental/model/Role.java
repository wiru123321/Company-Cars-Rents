package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "roles")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Role() {
    }

    public Role(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Role(final String name) {
        this.name = name;
    }
}
