package com.bytescolaborativos.gamehub.service;

import com.bytescolaborativos.gamehub.model.Role;
import com.bytescolaborativos.gamehub.model.Status;
import com.bytescolaborativos.gamehub.model.User;
import com.bytescolaborativos.gamehub.repository.UserRepository;
import com.bytescolaborativos.gamehub.security.services.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewPlayer(String username, String email, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.PLAYER);
        user.setRank(Status.CREATED);
        user.setPoints(0);

        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("No authenticated user found.");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetailsImpl) {
            UserDetailsImpl userDetails = (UserDetailsImpl) principal;
            return userRepository.findById(userDetails.getId())
                    .orElseThrow(() -> new UsernameNotFoundException("Authenticated user not found in database with ID: " + userDetails.getId()));
        } else if (principal instanceof String && "anonymousUser".equals(principal)) {
            throw new UsernameNotFoundException("No authenticated user found.");
        }
        throw new IllegalStateException("Unexpected principal type: " + principal.getClass().getName());
    }

}
