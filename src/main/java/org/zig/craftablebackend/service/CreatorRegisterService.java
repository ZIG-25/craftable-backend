package org.zig.craftablebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zig.craftablebackend.dto.*;
import org.zig.craftablebackend.infrastructure.database.*;
import org.zig.craftablebackend.infrastructure.repository.*;

@Service
public class CreatorRegisterService {
    @Autowired
    private CreatorRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String register(CreatorRegisterDto request) {
        if (repository.findCreatorByEmail(request.getEmail()).isPresent()) return "Email already in use.";
        Creator creator = new Creator();
        creator.setLogin(request.getLogin());
        creator.setPassword(passwordEncoder.encode(request.getPassword()));
        creator.setEmail(request.getEmail());
        creator.setName(request.getName());
        creator.setSurname(request.getSurname());
        creator.setBio(request.getBio());
        creator.setPhoneNumber(request.getPhoneNumber());
        repository.save(creator);
        return "Creator registration successful";
    }
}
