package com.study.expensetracking.repository;

import com.study.expensetracking.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
