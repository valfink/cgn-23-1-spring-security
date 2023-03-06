package de.neuefische.cgn231springsecurity.model;

public record MongoUserResponse(
        String id,
        String username,
        String role
) {
}
