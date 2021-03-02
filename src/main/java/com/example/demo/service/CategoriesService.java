package com.example.demo.service;

import com.example.demo.model.CategoriesEntity;
import com.example.demo.repository.CategoriesRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoriesService {

    private final CategoriesRepository repo;

    public CategoriesService(CategoriesRepository repo) {
        this.repo = repo;
    }

    public CategoriesEntity getOne(int id) {
        return repo.getOne(id);
    }

    public List<CategoriesEntity> findAll() {
        return repo.findAll();
    }
}
