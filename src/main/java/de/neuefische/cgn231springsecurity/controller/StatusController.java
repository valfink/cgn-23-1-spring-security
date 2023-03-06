package de.neuefische.cgn231springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/status")
public class StatusController {
    @GetMapping
    public String apiStatus() {
        return "We are live 🎉";
    }
}
