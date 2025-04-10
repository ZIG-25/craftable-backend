package org.zig.craftablebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zig.craftablebackend.dto.RegisterRequest;
import org.zig.craftablebackend.service.RegisterService;

@RestController
@RequestMapping("/user/create")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        String result = registerService.register(request);

        if (result.equals("Registration successful")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
}

