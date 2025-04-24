package org.zig.craftablebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.zig.craftablebackend.controllers.LoginResponseDto;
import org.zig.craftablebackend.database.Creator;
import org.zig.craftablebackend.database.Customer;
import org.zig.craftablebackend.database.models.LoginCredentials;
import org.zig.craftablebackend.repository.CreatorRepository;
import org.zig.craftablebackend.repository.CustomerRepository;

import java.util.Optional;

@Service
public class AuthService {
    private final CreatorRepository creatorRepository;
    private final CustomerRepository customerRepository;

    private final JwtService jwtService;

    @Autowired
    public AuthService(CreatorRepository creatorRepository, CustomerRepository customerRepository, JwtService jwtService) {
        this.creatorRepository = creatorRepository;
        this.customerRepository = customerRepository;
        this.jwtService = jwtService;
    }

    public LoginResponseDto login(LoginCredentials loginCredentials) {
        Optional<Customer> optional = customerRepository.findCustomerByEmail(loginCredentials.getEmail());
        if (optional.isEmpty()) {
            Creator creator = creatorRepository.findCreatorByEmail(loginCredentials.getEmail()).orElseThrow(RuntimeException::new);
            return loginCreator(creator, loginCredentials);
        }
        return loginCustomer(optional.get(), loginCredentials);
    }

    private LoginResponseDto loginCustomer (Customer customer, LoginCredentials loginCredentials) {
        if (!customer.getPassword().equals(loginCredentials.getPassword())) {
            throw new RuntimeException();
        }
        String token = jwtService.generateToken(customer);
        return new LoginResponseDto(token);
    }

    private LoginResponseDto loginCreator (Creator creator, LoginCredentials loginCredentials) {
        if (!creator.getPassword().equals(loginCredentials.getPassword())) {
            throw new RuntimeException();
        }
        String token = jwtService.generateToken(creator);
        return new LoginResponseDto(token);
    }
}
