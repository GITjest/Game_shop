package com.example.demo.service;

import com.example.demo.model.ReviewsEntity;
import com.example.demo.repository.ReviewsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReviewsService {
    private final ReviewsRepository repo;

    public ReviewsService(ReviewsRepository repo) {
        this.repo = repo;
    }

    public void save(ReviewsEntity reviewsEntity) {
        repo.save(reviewsEntity);
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }

    public ReviewsEntity getOne(int id) {
        return repo.getOne(id);
    }
}
