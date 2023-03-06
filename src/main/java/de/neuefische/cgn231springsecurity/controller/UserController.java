package de.neuefische.cgn231springsecurity.controller;

import de.neuefische.cgn231springsecurity.model.MongoUser;
import de.neuefische.cgn231springsecurity.repository.MongoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final MongoUserRepository mongoUserRepository;

    @PostMapping
    public MongoUser postUser(@RequestBody MongoUser mongoUser) {
        return mongoUserRepository.save(mongoUser);
    }

    @GetMapping("/me")
    public String getMe(Principal principal) {
        if (principal != null) {
            return principal.getName();
        }
        return "Anonymous user!";
    }

    @GetMapping("/me_context")
    public String getContextMe() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }
}
