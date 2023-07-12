package com.study.expensetracking.controller;

import com.study.expensetracking.dto.user.CreateUserDto;
import com.study.expensetracking.dto.user.UserDto;
import com.study.expensetracking.model.User;

import com.study.expensetracking.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;


    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto createUserDto) {
        User savedUser = userService.save(modelMapper.map(createUserDto, User.class));
        UserDto userDto = modelMapper.map(savedUser, UserDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserDto> userDtos = users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {

        User updatedUser = userService.update(id,(modelMapper.map(userDto, User.class)));
        UserDto updatedUserDto = modelMapper.map(updatedUser, UserDto.class);
        return ResponseEntity.ok(updatedUserDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

