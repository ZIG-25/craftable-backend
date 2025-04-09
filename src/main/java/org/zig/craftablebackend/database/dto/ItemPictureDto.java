package org.zig.craftablebackend.database.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPictureDto {
    private Integer id;
    private String photoUrl;
    private Integer itemForSaleId;
}
