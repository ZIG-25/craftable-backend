package org.zig.craftablebackend.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column()
    private String profession;

    @ManyToOne
    @JoinColumn(name="creator_id", nullable=false)
    private Creator creatorId;
}
