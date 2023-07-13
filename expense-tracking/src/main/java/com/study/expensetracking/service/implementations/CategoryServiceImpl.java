package com.study.expensetracking.service.implementations;

import com.study.expensetracking.exception.NotFoundException;
import com.study.expensetracking.model.Category;
import com.study.expensetracking.repository.CategoryRepository;
import com.study.expensetracking.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAlll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category update(Long id, Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setName(category.getName());
            return categoryRepository.save(existingCategory);
        }
        throw new NotFoundException("Category not found");
    }

    @Override
    public void Delete(Long id) {
        Category category=findById(id);
        categoryRepository.delete(category);

    }

    @Override
    public Category findById(Long id) {
        Category category=this.categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found with id: " + id));
        return category;
    }
}
