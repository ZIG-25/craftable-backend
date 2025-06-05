package org.zig.craftablebackend.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@Entity
public class ItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String deliveryAddress;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String status;

    @ManyToOne
    @JoinColumn(name="customer_id",  nullable=false)
    private Customer customerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_for_sale_id", referencedColumnName = "id")
    private ItemForSale itemForSale;
}

