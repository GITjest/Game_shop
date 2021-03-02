package com.example.demo.repository;

import com.example.demo.model.RequirementTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequirementTypeRepository extends JpaRepository<RequirementTypesEntity, Integer> {
}
