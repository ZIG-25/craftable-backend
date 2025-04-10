package org.zig.craftablebackend.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequest {
    private String role; // "CUSTOMER", "CREATOR", "ADMIN"
    private String login;
    private String password;
    private String email;

    private String name;
    private String surname;
    private String bio;
    private Integer phoneNumber;

    public RegisterRequest(String role, String login, String password, String email, String name, String surname, String bio, Integer phoneNumber) {
        this.role = role;
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.bio = bio;
        this.phoneNumber = phoneNumber;
    }

    public RegisterRequest() {
    }
}
