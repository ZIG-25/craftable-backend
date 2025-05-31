package org.zig.craftablebackend.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;
import org.zig.craftablebackend.infrastructure.entity.Creator;
import org.zig.craftablebackend.infrastructure.entity.ItemForSale;
import org.zig.craftablebackend.infrastructure.entity.ItemPicture;

import java.util.List;
import java.util.stream.Collectors;

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

    public static ItemForSale toEntity(ItemForSaleDto dto, Creator creator) {
        ItemForSale item = new ItemForSale();
        item.setTitle(dto.getTitle());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setCreatorId(creator);
        return item;
    }


    public static void updateEntity(ItemForSaleDto dto, ItemForSale item) {
        item.setTitle(dto.getTitle());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
    }


    public static ItemForSaleDto fromEntity(ItemForSale item) {
        ItemForSaleDto dto = new ItemForSaleDto();
        dto.setId(item.getId());
        dto.setTitle(item.getTitle());
        dto.setDescription(item.getDescription());
        dto.setPrice(item.getPrice());
        dto.setCreatorId(item.getCreatorId().getId());
        if (item.getItemOrder() != null) {
            dto.setItemOrderId(item.getItemOrder().getId());
        }
        if (item.getItemPictures() != null) {
            dto.setItemPictureIds(
                    item.getItemPictures().stream()
                            .map(ItemPicture::getId)
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }
}
