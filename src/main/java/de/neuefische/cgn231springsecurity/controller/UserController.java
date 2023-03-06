package de.neuefische.cgn231springsecurity.controller;

import de.neuefische.cgn231springsecurity.model.MongoUserRequest;
import de.neuefische.cgn231springsecurity.model.MongoUserResponse;
import de.neuefische.cgn231springsecurity.repository.MongoUserRepository;
import de.neuefische.cgn231springsecurity.service.MongoUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final MongoUserRepository mongoUserRepository;
    private final MongoUserDetailsService mongoUserDetailsService;

    @PostMapping
    public MongoUserResponse signup(@RequestBody MongoUserRequest mongoUser) {
        return mongoUserDetailsService.signup(mongoUser);
    }

    @GetMapping("/me")
    public String getMe(Principal principal) {
        if (principal != null) {
            return principal.getName() +
                    mongoUserRepository.findByUsername(principal.getName())
                            .orElseThrow(NoSuchElementException::new)
                            .toString();
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
