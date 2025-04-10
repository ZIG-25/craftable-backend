package org.zig.craftablebackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.zig.craftablebackend.dto.RegisterRequest;
import org.zig.craftablebackend.service.RegisterService;

@Component
public class TestRegisterRunner implements CommandLineRunner {

    private final RegisterService registerService;

    public TestRegisterRunner(RegisterService registerService) {
        this.registerService = registerService;
    }

    @Override
    public void run(String... args) {
        RegisterRequest request = new RegisterRequest();
        request.setLogin("testuser");
        request.setPassword("test123");
        request.setEmail("test@example.com");
        request.setRole("CUSTOMER");
        request.setName("Test");
        request.setSurname("User");
        request.setBio("Teścik");

        String result = registerService.register(request);
        System.out.println("REJESTRACJA: " + result);
    }
}

