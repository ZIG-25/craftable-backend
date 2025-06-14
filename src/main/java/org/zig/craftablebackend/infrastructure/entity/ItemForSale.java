package org.zig.craftablebackend.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class ItemForSale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer Id;

    private String description;

    private String title;

    private Integer price;

    @OneToOne(mappedBy = "itemForSale")
    private ItemOrder itemOrder;

    @ManyToOne
    @JoinColumn(name="creator_id", nullable=false)
    private Creator creatorId;

    @OneToMany(mappedBy="itemForSale", cascade = CascadeType.ALL)
    private List<ItemPicture> itemPictures;
}
