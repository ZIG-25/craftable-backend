package org.zig.craftablebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.zig.craftablebackend.infrastructure.database.Administrator;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class AdministratorDto {
    private Integer id;
    private String login;
    private String email;

    public static AdministratorDto toDto(Administrator administrator) {
        AdministratorDto dto = new AdministratorDto(
                administrator.getId(),
                administrator.getLogin(),
                administrator.getEmail()
        );
        return dto;
    }

    public static Administrator toEntity(AdministratorDto dto) {
        Administrator administrator = new Administrator();
        administrator.setId(dto.getId());
        administrator.setLogin(dto.getLogin());
        administrator.setEmail(dto.getEmail());
        return administrator;
    }
}
