package org.zig.craftablebackend.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zig.craftablebackend.dto.CustomerDto;
import org.zig.craftablebackend.infrastructure.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto getOneByEmail(String email) {
        return CustomerDto.toFullDto(
                customerRepository.findCustomerByEmail(email)
                        .orElseThrow(() -> new IllegalArgumentException("Customer with email " + email + " not found"))
        );
    }
}
