package org.zig.craftablebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.zig.craftablebackend.infrastructure.entity.Customer;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CustomerDto {
    private Integer id;
    private String password;
    private String login;
    private String email;
    private String bio;
    private String name;
    private String surname;

    public static CustomerDto fromEntity(Customer customer) {
        CustomerDto dto = new CustomerDto(
                customer.getId(),
                null,
                customer.getLogin(),
                customer.getEmail(),
                customer.getBio(),
                customer.getName(),
                customer.getSurname()
        );
        return dto;
    }

    public static Customer toEntity(CustomerDto dto) {
    Customer customer = new Customer();
    customer.setId(dto.getId());
    customer.setPassword(dto.getPassword());
    customer.setLogin(dto.getLogin());
    customer.setEmail(dto.getEmail());
    customer.setBio(dto.getBio());
    customer.setName(dto.getName());
    customer.setSurname(dto.getSurname());
    return customer;
    }
}
