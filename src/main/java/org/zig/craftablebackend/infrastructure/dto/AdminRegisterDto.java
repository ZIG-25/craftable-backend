package org.zig.craftablebackend.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AdminRegisterDto {
    private String login;
    private String password;
    private String email;
}