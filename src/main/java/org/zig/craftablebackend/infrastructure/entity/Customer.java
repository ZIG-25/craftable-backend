package org.zig.craftablebackend.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(unique = true)
    private String login;

    private String password;

    @Column(unique = true)
    private String email;

    private String bio;

    private String name;

    private String surname;

    @OneToMany(mappedBy="customerId")
    private List<ItemOrder> itemOrder;

    @OneToMany(mappedBy="customerId")
    private List<Request> requests;

    @OneToMany(mappedBy="customerId")
    private Set<Report> reports;
}
