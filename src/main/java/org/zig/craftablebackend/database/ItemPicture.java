package org.zig.craftablebackend.database;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ItemPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(unique = true)

    private String photoUrl;

    @ManyToOne
    @JoinColumn(name="item_sale_pictures", nullable=false)
    private ItemForSale itemForSale;
}
