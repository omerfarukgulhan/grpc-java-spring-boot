package com.ofg.discount.repository;

import com.ofg.discount.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByExternalId(String externalId);
}
