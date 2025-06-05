package org.zig.craftablebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zig.craftablebackend.infrastructure.entity.*;
import org.zig.craftablebackend.infrastructure.repository.*;

@Service
public class AdminRegisterService {
    @Autowired
    private AdministratorRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    public String register(AdminRegisterDto request) {
//        if (repository.findAdministratorByEmail(request.getEmail()).isPresent()) return "Email already in use.";
//        Administrator admin = new Administrator();
//        admin.setLogin(request.getLogin());
//        admin.setPassword(passwordEncoder.encode(request.getPassword()));
//        admin.setEmail(request.getEmail());
//        repository.save(admin);
//        return "Admin registration successful";
//    }
}
