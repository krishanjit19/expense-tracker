package com.expensetracker.controller;

import com.expensetracker.dto.LoginRequest;
import com.expensetracker.dto.SignupRequest;
import com.expensetracker.dto.AuthResponse;
import com.expensetracker.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
    try {
      AuthResponse response = authService.login(loginRequest);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      log.error("Login failed", e);
      return ResponseEntity.badRequest().body(new ErrorResponse("Invalid email or password"));
    }
  }

  @PostMapping("/signup")
  public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest) {
    try {
      AuthResponse response = authService.signup(signupRequest);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      log.error("Signup failed", e);
      return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }
  }

  private record ErrorResponse(String message) {}
}