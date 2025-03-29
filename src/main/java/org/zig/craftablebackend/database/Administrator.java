package org.zig.craftablebackend.database;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(unique = true)

    private String login;

    private String password;

    private String email;
}
