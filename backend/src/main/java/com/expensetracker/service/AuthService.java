package com.expensetracker.service;

import com.expensetracker.dto.AuthResponse;
import com.expensetracker.dto.LoginRequest;
import com.expensetracker.dto.SignupRequest;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);
    AuthResponse signup(SignupRequest signupRequest);
} 