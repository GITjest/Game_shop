package com.example.demo.service;

import com.example.demo.model.RolesEntity;
import com.example.demo.repository.RolesRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RolesService {
    private final RolesRepository repo;

    public RolesService(RolesRepository repo) {
        this.repo = repo;
    }

    public RolesEntity findByRole(String role) {
        return repo.findByRole(role);
    }

    public List<RolesEntity> findAll() {
        return repo.findAll();
    }

    public RolesEntity getOne(int id) {
        return repo.getOne(id);
    }
}
