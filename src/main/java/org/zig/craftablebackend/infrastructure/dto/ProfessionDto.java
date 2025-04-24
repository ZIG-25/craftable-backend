package org.zig.craftablebackend.infrastructure.dto;

import lombok.*;
import org.zig.craftablebackend.infrastructure.entity.Profession;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfessionDto {
    private Integer id;
    private String profession;
    private Integer creatorId;

    public ProfessionDto(String profession) {
        this.profession = profession;
    }

    public static ProfessionDto fromEntity(Profession profession) {
        return  new ProfessionDto(
                profession.getId(),
                profession.getProfession(),
                profession.getCreatorId().getId()
        );
    }

    public static Profession toEntity(ProfessionDto professionDto) {
        Profession profession = new Profession();
        profession.setId(professionDto.getId());
        profession.setProfession(professionDto.getProfession());

        return profession;
    }
}
