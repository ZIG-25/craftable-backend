package org.zig.craftablebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zig.craftablebackend.dto.*;
import org.zig.craftablebackend.infrastructure.database.*;
import org.zig.craftablebackend.infrastructure.repository.*;

@Service
public class CustomerRegisterService {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String register(CustomerRegisterDto request) {
        if (repository.findCustomerByEmail(request.getEmail()).isPresent()) return "Email already in use.";
        Customer customer = new Customer();
        customer.setLogin(request.getLogin());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setEmail(request.getEmail());
        customer.setName(request.getName());
        customer.setSurname(request.getSurname());
        customer.setBio(request.getBio());
        repository.save(customer);
        return "Customer registration successful";
    }
}
