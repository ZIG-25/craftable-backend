package org.zig.craftablebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zig.craftablebackend.dto.CustomerDto;
import org.zig.craftablebackend.infrastructure.entity.*;
import org.zig.craftablebackend.infrastructure.repository.*;

@Service
public class CustomerRegisterService {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String register(CustomerDto request) {
        if (repository.findCustomerByEmail(request.getEmail()).isPresent()) return "Email already in use.";
        Customer customer = CustomerDto.toEntity(request);
        customer.setPassword(passwordEncoder.encode(request.getPassword()));

        repository.save(customer);
        return "Customer registration successful";
    }
}
