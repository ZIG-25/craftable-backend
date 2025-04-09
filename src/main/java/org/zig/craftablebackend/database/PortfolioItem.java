package org.zig.craftablebackend.database;

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

    private String description;

    private String title;

    @ManyToOne
    @JoinColumn(name="creator_id", nullable=false)
    private Creator creatorId;
}
