package de.neuefische.cgn231springsecurity.model;

public record MongoUserRequest(
        String username,
        String password
) {
}
