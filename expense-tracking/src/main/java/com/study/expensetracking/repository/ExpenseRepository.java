package com.study.expensetracking.repository;

import com.study.expensetracking.dto.expense.ExpenseDto;
import com.study.expensetracking.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    boolean existsByExpenseName(String expenseName);

}
