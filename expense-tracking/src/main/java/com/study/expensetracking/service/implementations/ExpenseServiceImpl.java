package com.study.expensetracking.service.implementations;

import com.study.expensetracking.exception.AlreadyExistException;
import com.study.expensetracking.exception.NotFoundException;
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
            throw new AlreadyExistException("this expense already exist");
        }
        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense update(Long id,Expense newExpense) {
        Expense existingExpense = findById(id);
        existingExpense.setExpenseName(newExpense.getExpenseName());
        existingExpense.setAmount(newExpense.getAmount());
        existingExpense.setDate(newExpense.getDate());

        return expenseRepository.save(existingExpense);
    }

    @Override
    public void delete(Long id) {
        Expense expense = findById(id);
        expenseRepository.delete(expense);
    }

    @Override
    public Expense findById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Expense not found with id: " + id));
    }

    @Override
    public Double getTotalAmountByUserId(Long userId) {
        return expenseRepository.getTotalAmountByUserId(userId);
    }

}
