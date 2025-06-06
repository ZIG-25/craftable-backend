package org.zig.craftablebackend.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class PortfolioItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(unique = true)
    private String photoUrl;

    @Column(length = 1000)
    private String description;

    private String title;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    @JsonBackReference
    private Creator creatorId;
}
