package com.euvic.carrental.model;

import com.euvic.carrental.responses.User.UserCreation;
import com.euvic.carrental.responses.User.UserDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    private Boolean isActive = true;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Role role;

    public User() {
    }

    public User(Long id, final UserDTO userDTO, final Role role) {
        this.id = id;
        this.login = userDTO.getLogin();
        this.email = userDTO.getEmail();
        this.name = userDTO.getName();
        this.surname = userDTO.getSurname();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.role = role;
    }

    public User(Long id, final UserCreation userCreation, final Role role) {
        this.id = id;
        this.login = userCreation.getLogin();
        this.password = userCreation.getPassword();
        this.email = userCreation.getEmail();
        this.name = userCreation.getName();
        this.surname = userCreation.getSurname();
        this.phoneNumber = userCreation.getPhoneNumber();
        this.role = role;
    }

    public User(final Long id, final String login, final String password, final String email, final String name, final String surname, final String phoneNumber, final Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
}
