package org.zig.craftablebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zig.craftablebackend.dto.CreatorDto;
import org.zig.craftablebackend.dto.CreatorRegisterDto;
import org.zig.craftablebackend.service.CreatorRegisterService;

@RestController
@RequestMapping("/seller/create")
public class CreatorRegisterController {
    @Autowired
    private CreatorRegisterService service;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody CreatorRegisterDto request) {
        String result = service.register(request);

        if (result.equals("Creator registration successful")) {
            return ResponseEntity.ok(result); // HTTP 200
        } else {
            return ResponseEntity.badRequest().body(result); // HTTP 400
        }
    }
}