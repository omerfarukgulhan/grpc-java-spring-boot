package com.ofg.product.service;

import com.ofg.product.model.entity.Category;
import com.ofg.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category add(Category category) {
        return categoryRepository.save(category);
    }
}
