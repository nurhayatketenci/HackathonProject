package com.study.expensetracking.service;

import com.study.expensetracking.dto.expense.CreateExpenseDto;
import com.study.expensetracking.dto.expense.ExpenseDto;
import com.study.expensetracking.model.Expense;

import java.util.List;

public interface ExpenseService {
    ExpenseDto save(CreateExpenseDto createExpenseDto);
    List<ExpenseDto> findAll();
    ExpenseDto update(ExpenseDto newExpense);
    void delete(Long id);
    ExpenseDto findById(Long id);
}
