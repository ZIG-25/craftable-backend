package org.zig.craftablebackend.database.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemForSaleDto {
    private Integer id;
    private String description;
    private String title;
    private Integer price;

    private Integer itemOrderId;
    private Integer creatorId;
    private List<Integer> itemPictureIds;
}
