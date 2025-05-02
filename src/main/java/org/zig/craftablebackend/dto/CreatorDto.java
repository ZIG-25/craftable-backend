package org.zig.craftablebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.zig.craftablebackend.infrastructure.database.Creator;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CreatorDto {
    private Integer id;
    private String login;
    private String email;
    private String bio;
    private String name;
    private String surname;
    private Integer phoneNumber;

    public static CreatorDto toDto(Creator creator) {
        CreatorDto dto = new CreatorDto(
                creator.getId(),
                creator.getLogin(),
                creator.getEmail(),
                creator.getBio(),
                creator.getName(),
                creator.getSurname(),
                creator.getPhoneNumber()
        );
        return dto;
    }

    public static Creator toEntity(CreatorDto dto) {
        Creator creator = new Creator();
        creator.setId(dto.getId());
        creator.setLogin(dto.getLogin());
        creator.setEmail(dto.getEmail());
        creator.setBio(dto.getBio());
        creator.setName(dto.getName());
        creator.setSurname(dto.getSurname());
        creator.setPhoneNumber(dto.getPhoneNumber());
        return creator;
    }
}
