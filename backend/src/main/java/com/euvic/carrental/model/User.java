package com.euvic.carrental.model;

import com.euvic.carrental.responses.UserDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Role role;

    public User() {
    }

    public User(final UserDTO userDTO, final Role role) {
        this.login = userDTO.getLogin();
        this.password = userDTO.getPassword();
        this.email = userDTO.getEmail();
        this.name = userDTO.getName();
        this.surname = userDTO.getSurname();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.isActive = userDTO.getIsActive();
        this.role = role;
        this.id = null;
    }

    public User(final Long id, final String login, final String password, final String email, final String name, final String surname, final String phoneNumber, final Boolean isActive, final Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.role = role;
    }
}
