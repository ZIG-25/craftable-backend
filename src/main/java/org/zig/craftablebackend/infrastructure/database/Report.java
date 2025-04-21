package org.zig.craftablebackend.infrastructure.database;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.zig.craftablebackend.infrastructure.shared.UserRole;

import java.util.Date;

@Setter
@Getter
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(unique = true)
    private String description;

    private String title;

    private String status;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Enumerated(EnumType.STRING)
    private UserRole targetedUserRole;

    @ManyToOne
    @JoinColumn(name="creator_id", nullable=false)
    private Creator creatorId;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customerId;
}