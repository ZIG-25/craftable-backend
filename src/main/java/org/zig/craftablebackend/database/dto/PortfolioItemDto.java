package org.zig.craftablebackend.database.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioItemDto {
    private Integer id;
    private String photoUrl;
    private String description;
    private String title;
    private Integer creatorId;
}
