package com.study.expensetracking.controller;

import com.study.expensetracking.dto.expense.CreateExpenseDto;
import com.study.expensetracking.dto.expense.ExpenseDto;
import com.study.expensetracking.model.Expense;
import com.study.expensetracking.service.ExpenseService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


    @RestController
    @RequestMapping("/api/expenses")
    public class ExpenseController {
        private final ExpenseService expenseService;
        private final ModelMapper modelMapper;

        public ExpenseController(ExpenseService expenseService, ModelMapper modelMapper) {
            this.expenseService = expenseService;
            this.modelMapper = modelMapper;
        }

        @PostMapping("/save")
        public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto expenseDto) {
            Expense savedExpense = expenseService.save(modelMapper.map(expenseDto, Expense.class));
            return ResponseEntity.status(HttpStatus.CREATED).body(expenseDto);
        }

        @GetMapping("/getall")
        public ResponseEntity<List<ExpenseDto>> getAllExpenses() {
            List<Expense> expenses = expenseService.findAll();
            List<ExpenseDto> expenseDtos = expenses.stream()
                    .map(expense -> modelMapper.map(expense, ExpenseDto.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(expenseDtos);
        }

        @GetMapping("/findbyid/{id}")
        public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable Long id) {
            Expense expense = expenseService.findById(id);
            ExpenseDto expenseDto = modelMapper.map(expense, ExpenseDto.class);
            return ResponseEntity.ok(expenseDto);
        }

        @PutMapping("/update/{id}")
        public ResponseEntity<ExpenseDto> updateExpense(@PathVariable Long id, @RequestBody ExpenseDto expenseDto) {
            if (!id.equals(expenseDto.getId())) {
                throw new IllegalArgumentException("Mismatched id");
            }
            Expense updatedExpense = expenseService.update(id,(modelMapper.map(expenseDto, Expense.class)));
            ExpenseDto updatedExpenseDto = modelMapper.map(updatedExpense, ExpenseDto.class);
            return ResponseEntity.ok(updatedExpenseDto);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
            expenseService.delete(id);
            return ResponseEntity.noContent().build();
        }
        @GetMapping("/gettotalexpense/{userId}")
        public ResponseEntity<Double> getTotalExpenseByUserId(@PathVariable Long userId) {
            Double totalExpense = expenseService.getTotalAmountByUserId(userId);
            return ResponseEntity.ok(totalExpense);
        }

    }



