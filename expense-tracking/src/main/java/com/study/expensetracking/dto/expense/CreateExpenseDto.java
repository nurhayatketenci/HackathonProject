package com.study.expensetracking.dto.expense;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateExpenseDto {


    private String expenseName;

    private Double amount;

    private LocalDate date;

    private Long userId;
}
