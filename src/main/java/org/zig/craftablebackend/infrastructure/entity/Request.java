package org.zig.craftablebackend.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private Integer price;

    private String description;

    private String title;

    private String status;

    @Temporal(TemporalType.DATE)
    private Date deadline;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customerId;

    @ManyToOne
    @JoinColumn(name="creator_id", nullable=false)
    private Creator creatorId;
}

