package com.study.expensetracking.service;

import com.study.expensetracking.dto.user.CreateUserDto;
import com.study.expensetracking.dto.user.UserDto;
import com.study.expensetracking.model.User;

import java.util.List;

public interface UserService {
    User save(User user);
    List<User> findAll();
    User update(Long id,User newUser);
    void delete(Long id);
    User findById(Long id);

}
