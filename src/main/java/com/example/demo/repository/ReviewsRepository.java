package com.example.demo.repository;

import com.example.demo.model.ReviewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<ReviewsEntity, Integer> {
}
