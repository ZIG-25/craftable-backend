package org.zig.craftablebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zig.craftablebackend.infrastructure.model.LoginResponseDto;
import org.zig.craftablebackend.infrastructure.entity.Creator;
import org.zig.craftablebackend.infrastructure.entity.Customer;
import org.zig.craftablebackend.infrastructure.model.LoginCredentials;
import org.zig.craftablebackend.infrastructure.repository.CreatorRepository;
import org.zig.craftablebackend.infrastructure.repository.CustomerRepository;

import java.util.Optional;

@Service
public class AuthService {
    private final CreatorRepository creatorRepository;
    private final CustomerRepository customerRepository;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(CreatorRepository creatorRepository,
                       CustomerRepository customerRepository,
                       JwtService jwtService,
                       PasswordEncoder passwordEncoder) {
        this.creatorRepository = creatorRepository;
        this.customerRepository = customerRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDto login(LoginCredentials loginCredentials) {
        Optional<Customer> optional = customerRepository.findCustomerByEmail(loginCredentials.getEmail());
        if (optional.isEmpty()) {
            Creator creator = creatorRepository.findCreatorByEmail(loginCredentials.getEmail()).orElseThrow(RuntimeException::new);
            return loginCreator(creator, loginCredentials);
        }
        return loginCustomer(optional.get(), loginCredentials);
    }

    private LoginResponseDto loginCustomer(Customer customer, LoginCredentials loginCredentials) {
        if (!passwordEncoder.matches(loginCredentials.getPassword(), customer.getPassword())) {
            throw new RuntimeException();
        }
        String token = jwtService.generateToken(customer);
        return new LoginResponseDto(token);
    }

    private LoginResponseDto loginCreator(Creator creator, LoginCredentials loginCredentials) {
        if (!passwordEncoder.matches(loginCredentials.getPassword(), creator.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String token = jwtService.generateToken(creator);
        return new LoginResponseDto(token);
    }
}
