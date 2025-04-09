package org.zig.craftablebackend.database.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessionDto {
    private Integer id;
    private String profession;
    private Integer creatorId;
}
