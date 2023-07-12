package com.study.expensetracking.exception;


public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}

