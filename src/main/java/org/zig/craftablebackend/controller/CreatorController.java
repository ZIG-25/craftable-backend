package org.zig.craftablebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.zig.craftablebackend.dto.CreatorDto;
import org.zig.craftablebackend.service.CreatorService;
import org.zig.craftablebackend.service.JwtService;

import java.util.List;

@RestController
@RequestMapping("/api/creator")
public class CreatorController {
    private final CreatorService creatorService;
    private final JwtService jwtService;

    @Autowired
    public CreatorController(CreatorService creatorService, JwtService jwtService) {
        this.creatorService = creatorService;
        this.jwtService = jwtService;
    }

    @GetMapping("/all")
    public List<CreatorDto> getAllCreators() {
        return creatorService.getAll();
    }

    @GetMapping
    public CreatorDto getCreator(@RequestParam Integer id) {
        return creatorService.getOne(id);
    }

    @GetMapping("/self")
    public CreatorDto getSelf(@RequestHeader HttpHeaders headers) {
        return creatorService.getOneByEmail(jwtService.extractEmail(headers));
    }
}
