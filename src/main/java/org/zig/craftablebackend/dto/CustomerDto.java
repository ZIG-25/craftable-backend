package org.zig.craftablebackend.dto;

import lombok.*;
import org.zig.craftablebackend.infrastructure.entity.Customer;

import java.util.ArrayList;
import java.util.List;

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

    private List<RequestDto> requests = new ArrayList<>();

    public static CustomerDto fromEntity(Customer customer) {
        CustomerDto dto = new CustomerDto(
                customer.getId(),
                null,
                customer.getLogin(),
                customer.getEmail(),
                customer.getBio(),
                customer.getName(),
                customer.getSurname(),
                null
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

    public static CustomerDto toFullDto(Customer customer) {
        CustomerDto customerDto = CustomerDto.fromEntity(customer);
        customerDto.setRequests(
                customer.getRequests().stream().map(RequestDto::toDto).toList()
        );
        return customerDto;
    }
}
