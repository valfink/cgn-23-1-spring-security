package de.neuefische.cgn231springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secret")
public class SecretController {
    @GetMapping
    public String secretEndpoint() {
        return "THIS IS TOTALLY SECRET!";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Wow, you are an ADMIN!?!?";
    }
}
