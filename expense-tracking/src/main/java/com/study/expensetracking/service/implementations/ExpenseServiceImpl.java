package com.study.expensetracking.service.implementations;

import com.study.expensetracking.exception.NoDataFoundException;
import com.study.expensetracking.model.Expense;
import com.study.expensetracking.repository.ExpenseRepository;
import com.study.expensetracking.service.ExpenseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ModelMapper modelMapper) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Expense save(Expense expense) {
        boolean isExpenseExist=this.expenseRepository.existsByExpenseName(expense.getExpenseName());
        if(isExpenseExist){
            throw new RuntimeException("hata");
        }
        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense update(Expense newExpense) {
        Expense existingExpense = expenseRepository.findById(newExpense.getId())
                .orElseThrow(() -> new NoDataFoundException("Expense not found with id: " + newExpense.getId()));

        existingExpense.setExpenseName(newExpense.getExpenseName());
        existingExpense.setAmount(newExpense.getAmount());
        existingExpense.setDate(newExpense.getDate());

        return expenseRepository.save(existingExpense);
    }

    @Override
    public void delete(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new NoDataFoundException("Expense not found with id: " + id));

        expenseRepository.delete(expense);
    }

    @Override
    public Expense findById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new NoDataFoundException("Expense not found with id: " + id));
    }
}
