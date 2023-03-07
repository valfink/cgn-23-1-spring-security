package de.neuefische.cgn231springsecurity.service;

import de.neuefische.cgn231springsecurity.model.MongoUser;
import de.neuefische.cgn231springsecurity.model.MongoUserRequest;
import de.neuefische.cgn231springsecurity.model.MongoUserResponse;
import de.neuefische.cgn231springsecurity.repository.MongoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MongoUserDetailsService implements UserDetailsService {
    private final MongoUserRepository mongoUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final IDService idService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MongoUser mongoUser = mongoUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(
                mongoUser.username(),
                mongoUser.password(),
                List.of(new SimpleGrantedAuthority("ROLE_" + mongoUser.role()))
        );
    }

    public MongoUserResponse getMe(Principal principal) {
        MongoUser user = mongoUserRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return new MongoUserResponse(
                user.id(),
                user.username(),
                user.role()
        );
    }

    public MongoUserResponse signup(MongoUserRequest user) {
        if (user.username() == null || user.username().length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is required");
        }

        if (user.password() == null || user.password().length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is required");
        }

        if (mongoUserRepository.existsByUsername(user.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }

        MongoUser newUser = new MongoUser(
                idService.randomID(),
                user.username(),
                passwordEncoder.encode(user.password()),
                "BASIC"
        );

        MongoUser saved = mongoUserRepository.save(newUser);

        return new MongoUserResponse(
                saved.id(),
                saved.username(),
                saved.role()
        );
    }
}
