package org.zig.craftablebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.zig.craftablebackend.infrastructure.entity.PortfolioItem;

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
            portfolioItem.getPhotoUrl(),
            portfolioItem.getDescription(),
            portfolioItem.getTitle(),
            portfolioItem.getCreatorId().getId()
        );
    }
}
