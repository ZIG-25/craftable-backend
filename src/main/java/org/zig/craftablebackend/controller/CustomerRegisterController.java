package org.zig.craftablebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zig.craftablebackend.dto.*;
import org.zig.craftablebackend.service.*;

@RestController
@RequestMapping("/user/create")
public class CustomerRegisterController {
    @Autowired
    private CustomerRegisterService service;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody CustomerRegisterDto request) {
        String result = service.register(request);

        if (result.equals("Customer registration successful")) {
            return ResponseEntity.ok(result); // HTTP 200
        } else {
            return ResponseEntity.badRequest().body(result); // HTTP 400
        }
    }
}