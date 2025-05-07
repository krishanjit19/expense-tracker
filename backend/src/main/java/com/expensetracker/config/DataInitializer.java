package com.expensetracker.config;

import com.expensetracker.model.User;
import com.expensetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Check if test user already exists
        if (!userRepository.existsByEmail("krishanjit31122001@gmail.com")) {
            User testUser = new User();
            testUser.setEmail("krishanjit31122001@gmail.com");
            testUser.setPassword(passwordEncoder.encode("qwerty12345"));
            testUser.setFullName("Krishan Jit");
            testUser.setPhoneNumber("+919876543210");

            Set<String> roles = new HashSet<>();
            roles.add("ROLE_USER");
            testUser.setRoles(roles);

            userRepository.save(testUser);
            System.out.println("Test user created successfully!");
        }
    }
} 