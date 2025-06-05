package org.zig.craftablebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zig.craftablebackend.infrastructure.dto.CreatorDto;
import org.zig.craftablebackend.infrastructure.entity.*;
import org.zig.craftablebackend.infrastructure.repository.*;

@Service
public class CreatorRegisterService {
    private final CreatorRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public CreatorRegisterService(CreatorRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(CreatorDto request) {
        if (repository.findCreatorByEmail(request.getEmail()).isPresent()) return "Email already in use.";

        Creator creator = CreatorDto.toEntity(request);
        // Encode password
        creator.setPassword(passwordEncoder.encode(request.getPassword()));

        repository.save(creator);
        return "Creator registration successful";
    }
}
