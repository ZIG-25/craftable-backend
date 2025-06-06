package org.zig.craftablebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zig.craftablebackend.dto.CustomerDto;
import org.zig.craftablebackend.service.CustomerService;
import org.zig.craftablebackend.service.JwtService;

@RestController
@RequestMapping("/api/user")
public class CustomerController {
    private final CustomerService customerService;
    private final JwtService jwtService;

    @Autowired
    public CustomerController(CustomerService customerService, JwtService jwtService) {
        this.customerService = customerService;
        this.jwtService = jwtService;
    }

    @GetMapping("/self")
    public CustomerDto getSignedUser(@RequestHeader HttpHeaders headers) {
        return customerService.getOneByEmail(jwtService.extractEmail(headers));
    }

}
