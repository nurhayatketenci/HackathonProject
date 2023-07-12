package com.study.expensetracking.cronjob;

import com.study.expensetracking.model.Expense;
import com.study.expensetracking.repository.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class ExpenseAggregationJob {
    private static final Logger logger = LoggerFactory.getLogger(ExpenseAggregationJob.class);

    private final ExpenseRepository expenseRepository;

    public ExpenseAggregationJob(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void aggregateDailyExpenses() {
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusDays(1);
        LocalDate endDate = today; // Bugün

        List<Expense> dailyExpenses = expenseRepository.findByDateBetween(startDate, endDate);
        double totalDailyExpense = dailyExpenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
        logger.info("job output total expense: "+ totalDailyExpense);

    }

    @Scheduled(cron = "0 0 0 ? * MON")
    public void aggregateWeeklyExpenses() {
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusWeeks(1);
        LocalDate endDate = today;

        List<Expense> weeklyExpenses = expenseRepository.findByDateBetween(startDate, endDate);

        double totalWeeklyExpense = weeklyExpenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
        logger.info("job output total expense: "+ totalWeeklyExpense);

    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void aggregateMonthlyExpenses() {
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.withDayOfMonth(1);
        LocalDate endDate = today;

        List<Expense> monthlyExpenses = expenseRepository.findByDateBetween(startDate, endDate);
        double totalMonthlyExpense = monthlyExpenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        logger.info("job output total expense: "+ totalMonthlyExpense);
    }
}

