package de.neuefische.cgn231springsecurity.controller;

import de.neuefische.cgn231springsecurity.model.MongoUserRequest;
import de.neuefische.cgn231springsecurity.model.MongoUserResponse;
import de.neuefische.cgn231springsecurity.service.MongoUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final MongoUserDetailsService mongoUserDetailsService;

    @PostMapping
    public MongoUserResponse signup(@RequestBody MongoUserRequest mongoUser) {
        return mongoUserDetailsService.signup(mongoUser);
    }

    @PostMapping("/login")
    public MongoUserResponse login(Principal principal) {
        return getMe(principal);
    }

    @PostMapping("/logout")
    public void logout() {
        // doesn't need to do anythin, see FilterChainπ
    }

    @GetMapping("/me")
    public MongoUserResponse getMe(Principal principal) {
        return mongoUserDetailsService.getMe(principal);
    }

    @GetMapping("/me_context")
    public String getContextMe() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }
}
