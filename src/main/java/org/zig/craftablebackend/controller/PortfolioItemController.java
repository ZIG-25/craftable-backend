package org.zig.craftablebackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.zig.craftablebackend.dto.PortfolioItemDto;
import org.zig.craftablebackend.infrastructure.entity.Creator;
import org.zig.craftablebackend.infrastructure.entity.PortfolioItem;
import org.zig.craftablebackend.infrastructure.repository.CreatorRepository;
import org.zig.craftablebackend.service.JwtService;
import org.zig.craftablebackend.service.PortfolioItemService;
import org.zig.craftablebackend.shared.TokenUtils;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/portfolio-items")
public class PortfolioItemController {

    private final PortfolioItemService service;
    private final JwtService jwtService;
    private final CreatorRepository creatorRepository;
    private final TokenUtils tokenUtils;

    @Autowired
    public PortfolioItemController(TokenUtils tokenUtils, CreatorRepository creatorRepository,
                                   PortfolioItemService service, JwtService jwtService) {
        this.tokenUtils = tokenUtils;
        this.creatorRepository = creatorRepository;
        this.service = service;
        this.jwtService = jwtService;
    }

    @GetMapping
    public List<PortfolioItem> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<PortfolioItem> getById(@PathVariable Integer id) {
        return Optional.ofNullable(service.getById(id));
    }

    @PostMapping
    public PortfolioItemDto create(@RequestHeader HttpHeaders authHeader, @RequestBody PortfolioItemDto item) {
        return service.create(jwtService.extractEmail(authHeader), item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/creator/{creatorId}")
    public List<PortfolioItem> getByCreator(@PathVariable Integer creatorId) {
        Creator creator = creatorRepository.findById(creatorId)
                .orElseThrow(() -> new RuntimeException("Creator not found"));
        return service.getByCreator(creator);
    }
}

