package com.study.expensetracking.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
    private String phoneNumber;
}