package org.zig.craftablebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zig.craftablebackend.dto.RegisterRequest;
import org.zig.craftablebackend.infrastructure.database.Administrator;
import org.zig.craftablebackend.infrastructure.database.Creator;
import org.zig.craftablebackend.infrastructure.database.Customer;
import org.zig.craftablebackend.infrastructure.repository.AdministratorRepository;
import org.zig.craftablebackend.infrastructure.repository.CreatorRepository;
import org.zig.craftablebackend.infrastructure.repository.CustomerRepository;

@Service
public class RegisterService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CreatorRepository creatorRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {
        String role = request.getRole().toUpperCase();

        if (administratorRepository.findByLogin(request.getLogin()).isPresent() ||
                customerRepository.findByLogin(request.getLogin()).isPresent() ||
                creatorRepository.findByLogin(request.getLogin()).isPresent()) {
            return "Login already exists.";
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        switch (role) {
            case "ADMIN":
                Administrator admin = new Administrator();
                admin.setLogin(request.getLogin());
                admin.setPassword(hashedPassword);
                admin.setEmail(request.getEmail());
                administratorRepository.save(admin);
                break;

            case "CUSTOMER":
                Customer customer = new Customer();
                customer.setLogin(request.getLogin());
                customer.setPassword(hashedPassword);
                customer.setEmail(request.getEmail());
                customer.setName(request.getName());
                customer.setSurname(request.getSurname());
                customer.setBio(request.getBio());
                customerRepository.save(customer);
                break;

            case "CREATOR":
                Creator creator = new Creator();
                creator.setLogin(request.getLogin());
                creator.setPassword(hashedPassword);
                creator.setEmail(request.getEmail());
                creator.setName(request.getName());
                creator.setSurname(request.getSurname());
                creator.setBio(request.getBio());
                creator.setPhone_number(request.getPhoneNumber());
                creatorRepository.save(creator);
                break;

            default:
                return "Invalid role";
        }

        return "Registration successful";
    }
}
