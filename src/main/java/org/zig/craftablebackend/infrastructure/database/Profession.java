package org.zig.craftablebackend.infrastructure.database;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(unique = true)
    private String profession;

    @ManyToOne
    @JoinColumn(name="creator_id", nullable=false)
    private Creator creatorId;
}
