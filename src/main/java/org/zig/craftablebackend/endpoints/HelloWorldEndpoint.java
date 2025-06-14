package org.zig.craftablebackend.endpoints;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class HelloWorldEndpoint {
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }
}
