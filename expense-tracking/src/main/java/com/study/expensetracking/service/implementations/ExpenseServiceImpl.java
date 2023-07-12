package com.study.expensetracking.service.implementations;

import com.study.expensetracking.dto.expense.CreateExpenseDto;
import com.study.expensetracking.dto.expense.ExpenseDto;
import com.study.expensetracking.exception.NoDataFoundException;
import com.study.expensetracking.model.Expense;
import com.study.expensetracking.repository.ExpenseRepository;
import com.study.expensetracking.service.ExpenseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ModelMapper modelMapper) {
        this.expenseRepository = expenseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ExpenseDto save(CreateExpenseDto createExpenseDto) {
        boolean isExpenseExist = this.expenseRepository.existsByExpenseName(createExpenseDto.getExpenseName());
        if (isExpenseExist) {
            throw new RuntimeException("This expense already exists.");
        }
        Expense expenseToSave=this.modelMapper.map(createExpenseDto,Expense.class);
        return this.modelMapper.map(this.expenseRepository.save(expenseToSave), ExpenseDto.class);
    }


    @Override
    public List<ExpenseDto> findAll() {
        List<ExpenseDto> expenses=expenseRepository.findAll()
                .stream()
                .map(campaign -> this.modelMapper.map(campaign,ExpenseDto.class))
                .collect(Collectors.toList());
        return expenses;
    }

    @Override
    public ExpenseDto findById(Long id) {
        Expense expense = this.expenseRepository.findById(id)
                .orElseThrow(() -> new NoDataFoundException("Expense not found with id: " + id));

        ExpenseDto expenseDto = this.modelMapper.map(expense, ExpenseDto.class);
        return expenseDto;
    }

    @Override
    public ExpenseDto update(ExpenseDto newExpense) {
        Optional<Expense> expenseOptional = this.expenseRepository.findById(newExpense.getId());
        if (expenseOptional.isPresent()) {
            Expense expense = expenseOptional.get();
            expense.setExpenseName(newExpense.getExpenseName());
            expense.setAmount(newExpense.getAmount());
            expense.setDate(newExpense.getDate());
            this.expenseRepository.save(expense);
            return newExpense;
        } else {
            throw new NoDataFoundException("Expense not found with id: " + newExpense.getId());
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Expense> expenseOptional = this.expenseRepository.findById(id);
        if (expenseOptional.isPresent()) {
            Expense expense = expenseOptional.get();
            this.expenseRepository.delete(expense);
        }
    }
}
