package com.expensetracker.service;

import com.expensetracker.model.Expense;
import java.util.List;

public interface ExpenseService {
    List<Expense> getUserExpenses(Long userId);
    Expense createExpense(Expense expense, Long userId);
    Expense updateExpense(Long id, Expense expense, Long userId);
    void deleteExpense(Long id, Long userId);
} 