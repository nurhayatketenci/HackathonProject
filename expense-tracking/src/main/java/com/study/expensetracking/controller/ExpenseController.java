package com.study.expensetracking.controller;

import com.study.expensetracking.dto.expense.CreateExpenseDto;
import com.study.expensetracking.dto.expense.ExpenseDto;
import com.study.expensetracking.exception.NoDataFoundException;
import com.study.expensetracking.model.Expense;
import com.study.expensetracking.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    //veriyi eklerken diğerini siliyor?
    @PostMapping
    public ResponseEntity<ExpenseDto> save(@RequestBody CreateExpenseDto createExpenseDto){
        return ResponseEntity.ok(this.expenseService.save(createExpenseDto));
    }
    @GetMapping("/getall")
    public ResponseEntity<List<ExpenseDto>> findAll(){
        return ResponseEntity.ok(this.expenseService.findAll());
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<ExpenseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.expenseService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<ExpenseDto> update(@RequestBody ExpenseDto expenseDto){
        return ResponseEntity.ok(this.expenseService.update(expenseDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            expenseService.delete(id);
            return ResponseEntity.ok("Expense deleted successfully.");
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found with id: " + id);
        }
    }

}
