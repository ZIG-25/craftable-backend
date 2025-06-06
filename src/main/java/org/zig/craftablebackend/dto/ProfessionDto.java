package org.zig.craftablebackend.dto;

import lombok.Getter;
import lombok.Setter;
import org.zig.craftablebackend.infrastructure.entity.Profession;

@Getter
@Setter
public class ProfessionDto {
    private Integer id;
    private String profession;
    private Integer creatorId;

    public static Profession fromDto(ProfessionDto professionDto) {
        Profession profession = new Profession();
        profession.setId(professionDto.getId());
        profession.setProfession(professionDto.getProfession());
        return profession;
    }


    public static Profession fromDto(String prof) {
        Profession profession = new Profession();
        profession.setProfession(prof);
        return profession;
    }
}
