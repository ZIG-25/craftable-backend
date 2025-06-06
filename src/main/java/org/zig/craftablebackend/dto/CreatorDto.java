package org.zig.craftablebackend.dto;

import lombok.*;
import org.zig.craftablebackend.infrastructure.entity.Creator;
import org.zig.craftablebackend.infrastructure.entity.Profession;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CreatorDto {
    private Integer id;
    private String login;
    private String email;
    private String bio;
    private String password = null;
    private String name;
    private String surname;
    private Integer phoneNumber;
    private List<String> professions;
    private List<ItemForSaleDto> storeItems = new ArrayList<>();
    private List<PortfolioItemDto> portfolioItems = new ArrayList<>();
    private List<RequestDto> requests = new ArrayList<>();

    private CreatorDto(Integer id,
                       String login,
                       String email,
                       String bio,
                       String name,
                       String surname,
                       Integer phoneNumber,
                       List<String> professions) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.bio = bio;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.professions = professions;

    }


    public static CreatorDto toDto(Creator creator) {
        CreatorDto dto = new CreatorDto(
                creator.getId(),
                creator.getLogin(),
                creator.getEmail(),
                creator.getBio(),
                creator.getName(),
                creator.getSurname(),
                creator.getPhoneNumber(),
                creator.getProfessions().stream().map(Profession::getProfession).toList()
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

    public static CreatorDto toFullDto(Creator creator) {
        CreatorDto dto = new CreatorDto(
                creator.getId(),
                creator.getLogin(),
                creator.getEmail(),
                creator.getBio(),
                null,
                creator.getName(),
                creator.getSurname(),
                creator.getPhoneNumber(),
                creator.getProfessions().stream().map(Profession::getProfession).toList(),
                creator.getItemsForSale().stream().map(ItemForSaleDto::toDto).toList(),
                creator.getPortfolioItems().stream().map(PortfolioItemDto::toDto).toList(),
                creator.getRequests().stream().map(RequestDto::toDto).toList()
        );

        return dto;
    }
}
