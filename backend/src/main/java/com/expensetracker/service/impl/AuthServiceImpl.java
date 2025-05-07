package com.expensetracker.service.impl;

import com.expensetracker.dto.AuthResponse;
import com.expensetracker.dto.LoginRequest;
import com.expensetracker.dto.SignupRequest;
import com.expensetracker.model.User;
import com.expensetracker.repository.UserRepository;
import com.expensetracker.security.JwtTokenProvider;
import com.expensetracker.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        log.info("Attempting login for user: {}", loginRequest.getEmail());
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );

            User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

            String token = jwtTokenProvider.generateToken(user);
            log.info("Login successful for user: {}", user.getEmail());
            return new AuthResponse(token, user.getEmail(), user.getFullName());
        } catch (Exception e) {
            log.error("Login failed for user: {}", loginRequest.getEmail(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public AuthResponse signup(SignupRequest signupRequest) {
        log.info("Attempting signup for user: {}", signupRequest.getEmail());
        try {
            if (userRepository.existsByEmail(signupRequest.getEmail())) {
                log.warn("Email already exists: {}", signupRequest.getEmail());
                throw new RuntimeException("Email already exists");
            }

            User user = new User();
            user.setEmail(signupRequest.getEmail());
            user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
            user.setFullName(signupRequest.getFullName());
            user.setPhoneNumber(signupRequest.getPhoneNumber());

            Set<String> roles = new HashSet<>();
            roles.add("ROLE_USER");
            user.setRoles(roles);

            User savedUser = userRepository.save(user);
            log.info("User created successfully: {}", savedUser.getEmail());

            String token = jwtTokenProvider.generateToken(user);
            return new AuthResponse(token, user.getEmail(), user.getFullName());
        } catch (Exception e) {
            log.error("Signup failed for user: {}", signupRequest.getEmail(), e);
            throw e;
        }
    }
} 