package org.zig.craftablebackend.infrastructure.database;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
public class Creator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(unique = true)
    private String login;

    private String password;

    private String email;

    private String bio;

    private String name;

    private String surname;

    private Integer phoneNumber;

    @OneToMany(mappedBy="creatorId")
    private List<Profession> professions;
    
    @OneToMany(mappedBy = "creatorId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PortfolioItem> portfolioItems;

    @OneToMany(mappedBy="creatorId")
    private List<ItemForSale> itemsForSale;

    @OneToMany(mappedBy="creatorId")
    private List<Request> requests;

    @OneToMany(mappedBy="creatorId")
    private Set<Report> reports;
}
