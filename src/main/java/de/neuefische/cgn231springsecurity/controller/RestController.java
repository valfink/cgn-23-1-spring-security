package de.neuefische.cgn231springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/secret")
public class RestController {
    @GetMapping
    public String secretEndpoint() {
        return "THIS IS TOTALLY SECRET!";
    }
}
