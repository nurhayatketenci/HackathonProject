package com.study.expensetracking.service;

import com.study.expensetracking.dto.AuthenticationResponse;
import com.study.expensetracking.dto.RegisterRequest;

public interface AuthService {
    public AuthenticationResponse register(RegisterRequest request);
    public AuthenticationResponse authenticate(RegisterRequest request);
}
