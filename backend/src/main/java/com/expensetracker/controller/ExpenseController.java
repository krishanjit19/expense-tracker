package com.expensetracker.controller;

import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<Expense>> getUserExpenses(Authentication authentication) {
        Long userId = ((com.expensetracker.model.User) authentication.getPrincipal()).getId();
        return ResponseEntity.ok(expenseService.getUserExpenses(userId));
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(
            @RequestBody Expense expense,
            Authentication authentication) {
        Long userId = ((com.expensetracker.model.User) authentication.getPrincipal()).getId();
        return ResponseEntity.ok(expenseService.createExpense(expense, userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(
            @PathVariable Long id,
            @RequestBody Expense expense,
            Authentication authentication) {
        Long userId = ((com.expensetracker.model.User) authentication.getPrincipal()).getId();
        return ResponseEntity.ok(expenseService.updateExpense(id, expense, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(
            @PathVariable Long id,
            Authentication authentication) {
        Long userId = ((com.expensetracker.model.User) authentication.getPrincipal()).getId();
        expenseService.deleteExpense(id, userId);
        return ResponseEntity.ok().build();
    }
} 