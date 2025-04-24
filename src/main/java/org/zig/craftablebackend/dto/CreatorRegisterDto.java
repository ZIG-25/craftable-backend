package org.zig.craftablebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CreatorRegisterDto {
    private String login;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String bio;
    private Integer phoneNumber;
}