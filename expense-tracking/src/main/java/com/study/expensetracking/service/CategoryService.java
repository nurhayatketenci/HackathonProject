package com.study.expensetracking.service;

import com.study.expensetracking.model.Category;

import java.util.List;

public interface CategoryService {
    Category save(Category category);
    List<Category> findAlll();
    Category update(Long id , Category category);
    void Delete(Long id);
    Category findById(Long id);
}
