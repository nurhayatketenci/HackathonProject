package com.study.expensetracking.service.implementations;

import com.study.expensetracking.exception.NotFoundException;
import com.study.expensetracking.model.User;
import com.study.expensetracking.repository.UserRepository;
import com.study.expensetracking.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public User save(User user) {
        boolean isUserExist = this.userRepository.existsByEmail(user.getEmail());
        if (isUserExist) {
            throw new RuntimeException("already exist");
        }
        return this.userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = this.userRepository.findAll();
        return users;
    }

    @Override
    public User update(Long id, User newUser) {
        User existUser = findById(id);
        existUser.setFirstName(newUser.getFirstName());
        existUser.setLastName(newUser.getLastName());
        existUser.setEmail(newUser.getEmail());
        existUser.setPhoneNumber(newUser.getPhoneNumber());
        this.userRepository.save(existUser);
        return newUser;
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);
        this.userRepository.delete(user);
    }

    @Override
    public User findById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("Couldnt find lesson by id: " + id));
        return user;
    }

}
