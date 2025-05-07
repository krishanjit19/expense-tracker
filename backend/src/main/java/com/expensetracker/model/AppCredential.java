package com.expensetracker.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "app_credentials")
public class AppCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AppType appType;

    @Column(nullable = false)
    private String phoneNumber;

    @Column
    private String otp;

    @Column
    private LocalDateTime otpExpiry;

    @Column
    private String accessToken;

    @Column
    private LocalDateTime tokenExpiry;

    @Column
    private String refreshToken;

    public enum AppType {
        SWIGGY,
        ZOMATO
    }
} 