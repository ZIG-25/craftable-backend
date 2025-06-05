package org.zig.craftablebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.zig.craftablebackend.infrastructure.entity.ItemPicture;
import org.zig.craftablebackend.service.PictureSevice;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPictureDto {
    private Integer id;
    private String photoUrl;
    private Integer itemForSaleId;

    public static ItemPictureDto toDto(ItemPicture itemPicture) {
        return new ItemPictureDto(
            itemPicture.getId(),
            PictureSevice.convertImageToBase64(itemPicture.getPhotoUrl()),
            itemPicture.getItemForSale().getId()
        );
    }

    public static ItemPicture fromDto(ItemPictureDto itemPictureDto) {
        ItemPicture itemPicture = new ItemPicture();
        itemPicture.setId(itemPictureDto.getId());
        itemPicture.setPhotoUrl(itemPictureDto.getPhotoUrl());

        return itemPicture;
    }
}
