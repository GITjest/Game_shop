package com.example.demo.repository;

import com.example.demo.model.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Integer> {
}
