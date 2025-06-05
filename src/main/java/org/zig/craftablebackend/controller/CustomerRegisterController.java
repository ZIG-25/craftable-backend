package org.zig.craftablebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zig.craftablebackend.infrastructure.dto.CustomerDto;
import org.zig.craftablebackend.service.*;

@RestController
@RequestMapping("api/user")
public class CustomerRegisterController {
    private CustomerRegisterService service;

    @Autowired
    public CustomerRegisterController(CustomerRegisterService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<String> register(@RequestBody CustomerDto request) {
        String result = service.register(request);

        if (result.equals("Customer registration successful")) {
            return ResponseEntity.ok(result); // HTTP 200
        } else {
            return ResponseEntity.badRequest().body(result); // HTTP 400
        }
    }
}