package de.neuefische.cgn231springsecurity.model;

public record MongoUser(
        String id,
        String username,
        String password
) {
}
