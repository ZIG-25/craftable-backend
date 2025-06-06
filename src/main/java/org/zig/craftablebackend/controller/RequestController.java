package org.zig.craftablebackend.controller;

import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zig.craftablebackend.dto.ItemOrderDto;
import org.zig.craftablebackend.dto.RequestDto;
import org.zig.craftablebackend.service.JwtService;
import org.zig.craftablebackend.service.RequestService;

@RestController
@RequestMapping("/api/request-new-creation")
public class RequestController {
    private final RequestService requestService;
    private final JwtService jwtService;

    @Autowired
    public RequestController(RequestService requestService, JwtService jwtService) {
        this.requestService = requestService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<RequestDto> createRequest(@RequestBody RequestDto requestDto, @RequestHeader HttpHeaders headers) {
        try {
            return ResponseEntity.ok(requestService.createNew(requestDto, jwtService.extractEmail(headers)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<RequestDto> updateRequest(@RequestBody RequestDto requestDto) {
        try {
            return ResponseEntity.ok(requestService.updateStatus(requestDto));
        }  catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
