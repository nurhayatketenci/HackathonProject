package com.study.expensetracking.repository;
import com.study.expensetracking.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    boolean existsByExpenseName(String expenseName);
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.user.id = :userId")
    Double getTotalAmountByUserId(Long userId);

     List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);


}
