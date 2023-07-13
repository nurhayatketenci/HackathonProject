package com.study.expensetracking.service.implementations;

import com.study.expensetracking.dto.AuthenticationResponse;
import com.study.expensetracking.dto.RegisterRequest;
import com.study.expensetracking.model.Role;
import com.study.expensetracking.model.User;
import com.study.expensetracking.repository.UserRepository;
import com.study.expensetracking.security.JwtService;
import com.study.expensetracking.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        var user= User.builder().firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();


    }

    @Override
    @Transactional
    public AuthenticationResponse authenticate(RegisterRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user=userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }
}
