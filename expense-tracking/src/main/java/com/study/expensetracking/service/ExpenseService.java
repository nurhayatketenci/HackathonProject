package com.study.expensetracking.service;

import com.study.expensetracking.dto.expense.CreateExpenseDto;
import com.study.expensetracking.dto.expense.ExpenseDto;
import com.study.expensetracking.model.Expense;

import java.util.List;

public interface ExpenseService {
    Expense save(Expense expense);
    List<Expense> findAll();
    Expense update(Expense newExpense);
    void delete(Long id);
    Expense findById(Long id);
}
