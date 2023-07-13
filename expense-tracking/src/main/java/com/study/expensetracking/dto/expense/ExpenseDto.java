package com.study.expensetracking.dto.expense;


import com.study.expensetracking.model.Expense;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseDto {

    private Long id;

    private String expenseName;

    private Double amount;

    private LocalDate date;

    private Long userId;
    private Long categoryId;

    public static ExpenseDto from(Expense expense){
        return ExpenseDto.builder()
                .id(expense.getId())
                .expenseName(expense.getExpenseName())
                .amount(expense.getAmount()).date(expense.getDate())
                .userId(expense.getUser().getId()).build();
    }

}
