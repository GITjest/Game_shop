package com.example.demo.service;

import com.example.demo.model.RequirementTypesEntity;
import com.example.demo.repository.RequirementTypeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RequirementTypeService {
    private final RequirementTypeRepository repo;

    public RequirementTypeService(RequirementTypeRepository repo) {
        this.repo = repo;
    }

    public List<RequirementTypesEntity> find() {
        return repo.findAll();
    }
}
