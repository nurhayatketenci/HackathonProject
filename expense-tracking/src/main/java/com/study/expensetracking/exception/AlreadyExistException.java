package com.study.expensetracking.exception;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String message) {
        super(message);
    }

}
