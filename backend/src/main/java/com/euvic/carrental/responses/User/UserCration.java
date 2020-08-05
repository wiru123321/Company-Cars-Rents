package com.euvic.carrental.responses.User;
import com.euvic.carrental.model.User;
import com.euvic.carrental.responses.RoleDTO;
import lombok.Data;

@Data
public class UserCration {
    private String login;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private RoleDTO roleDTO;

    public  UserCration(){

    }

    public UserCration(final User user, final RoleDTO roleDTO) {
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.phoneNumber = user.getPhoneNumber();
        this.roleDTO = roleDTO;
    }

    public UserCration(final String login, final String password, final String email, final String name, final String surname, final String phoneNumber, final RoleDTO roleDTO) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.roleDTO = roleDTO;
    }
}
