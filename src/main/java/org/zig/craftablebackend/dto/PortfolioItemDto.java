package org.zig.craftablebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.zig.craftablebackend.infrastructure.entity.PortfolioItem;
import org.zig.craftablebackend.service.PictureSevice;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioItemDto {
    private Integer id;
    private String photoUrl;
    private String description;
    private String title;
    private Integer creatorId;

    public static PortfolioItemDto toDto(PortfolioItem portfolioItem) {
        return new PortfolioItemDto(
                portfolioItem.getId(),
                PictureSevice.convertImageToBase64(portfolioItem.getPhotoUrl()),
                portfolioItem.getDescription(),
                portfolioItem.getTitle(),
                portfolioItem.getCreatorId().getId()
        );
    }

    public static PortfolioItem fromDto(PortfolioItemDto portfolioItem) {
        PortfolioItem item = new PortfolioItem();
        item.setId(portfolioItem.getId());
        item.setPhotoUrl(portfolioItem.getPhotoUrl());
        item.setDescription(portfolioItem.getDescription());
        item.setTitle(portfolioItem.getTitle());
        return item;
    }
}
