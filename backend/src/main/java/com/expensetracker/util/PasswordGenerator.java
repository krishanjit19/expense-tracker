package com.expensetracker.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "qwerty12345";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Original password: " + rawPassword);
        System.out.println("Encoded password: " + encodedPassword);
        
        // Verify the password
        boolean matches = encoder.matches(rawPassword, encodedPassword);
        System.out.println("Password matches: " + matches);
    }
} 