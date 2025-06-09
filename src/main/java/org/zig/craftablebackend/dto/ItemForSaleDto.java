package org.zig.craftablebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.zig.craftablebackend.infrastructure.entity.Creator;
import org.zig.craftablebackend.infrastructure.entity.ItemForSale;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemForSaleDto {
    private Integer id;
    private String description;
    private String title;
    private Integer price;

    private Integer itemOrderId;
    private CreatorDto creatorId;
    private List<ItemPictureDto> itemPictureIds;

    public static ItemForSaleDto toDto(ItemForSale itemForSale) {
        return new ItemForSaleDto(
                itemForSale.getId(),
                itemForSale.getDescription(),
                itemForSale.getTitle(),
                itemForSale.getPrice(),
                itemForSale.getItemOrder() != null ? itemForSale.getItemOrder().getId() : null,
                CreatorDto.toDto(itemForSale.getCreatorId()),
                itemForSale.getItemPictures().stream().map(ItemPictureDto::toDto).toList()
        );
    }

    public static ItemForSale fromDto(ItemForSaleDto itemForSale) {
        ItemForSale item = new ItemForSale();
        item.setId(itemForSale.getId());
        item.setDescription(itemForSale.getDescription());
        item.setTitle(itemForSale.getTitle());
        item.setPrice(itemForSale.getPrice());
        item.setItemPictures(itemForSale.getItemPictureIds().stream().map(ItemPictureDto::fromDto).peek(it -> it.setItemForSale(item)).toList());

        return item;
    }
}
