package org.zig.craftablebackend.infrastructure.dto;

import lombok.*;
import org.zig.craftablebackend.infrastructure.entity.Creator;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class CreatorDto {
    private Integer id;
    private String login;
    private String password;
    private String email;
    private String bio;
    private String name;
    private String surname;
    private Integer phoneNumber;

    private List<ProfessionDto> professions;

    public static CreatorDto toDto(Creator creator) {
        CreatorDto dto = new CreatorDto(
                creator.getId(),
                creator.getLogin(),
                creator.getPassword(),
                creator.getEmail(),
                creator.getBio(),
                creator.getName(),
                creator.getSurname(),
                creator.getPhoneNumber(),
                creator.getProfessions().stream().map(ProfessionDto::fromEntity).toList()
        );
        return dto;
    }

    public static Creator toEntity(CreatorDto dto) {
        Creator creator = new Creator();
        creator.setId(dto.getId());
        creator.setLogin(dto.getLogin());
        creator.setPassword(dto.getPassword());
        creator.setEmail(dto.getEmail());
        creator.setBio(dto.getBio());
        creator.setName(dto.getName());
        creator.setSurname(dto.getSurname());
        creator.setPhoneNumber(dto.getPhoneNumber());
        creator.setProfessions(dto.professions.stream()
                .map(ProfessionDto::toEntity)
                .peek(it -> it.setCreatorId(creator))
                .toList());
        return creator;
    }
}
