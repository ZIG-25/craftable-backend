package org.zig.craftablebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zig.craftablebackend.infrastructure.dto.CreatorDto;
import org.zig.craftablebackend.dto.CreatorDto;
import org.zig.craftablebackend.dto.CreatorRegisterDto;
import org.zig.craftablebackend.service.CreatorRegisterService;

@RestController
@RequestMapping("api/seller")
public class CreatorRegisterController {
    private final CreatorRegisterService service;

    @Autowired
    public CreatorRegisterController(CreatorRegisterService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<String> register(@RequestBody CreatorDto request) {
        System.out.println(request);
        String result = service.register(request);

        if (result.equals("Creator registration successful")) {
            return ResponseEntity.ok(result); // HTTP 200
        } else {
            return ResponseEntity.badRequest().body(result); // HTTP 400
        }
    }
}