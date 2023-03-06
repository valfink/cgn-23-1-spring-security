package de.neuefische.cgn231springsecurity.controller;

import de.neuefische.cgn231springsecurity.model.MongoUser;
import de.neuefische.cgn231springsecurity.repository.MongoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final MongoUserRepository mongoUserRepository;

    @PostMapping
    public MongoUser postUser(@RequestBody MongoUser mongoUser) {
        return mongoUserRepository.save(mongoUser);
    }
}
