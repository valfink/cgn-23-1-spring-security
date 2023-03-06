package de.neuefische.cgn231springsecurity.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IDService {
    public String randomID() {
        return UUID.randomUUID().toString();
    }
}
