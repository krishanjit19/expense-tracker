package com.expensetracker.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AppCredential.AppType appType;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column
    private String restaurantName;

    @Column
    private String items;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column
    private String paymentMethod;

    @Column
    private String status;

    @Column
    private String notes;
} 