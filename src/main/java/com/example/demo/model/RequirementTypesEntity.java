package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "requirement_types", schema = "game_shop")
public class RequirementTypesEntity {
    private int requirementTypeId;
    private String name;
    private Collection<RequirementsEntity> requirements;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requirement_type_id")
    public int getRequirementTypeId() {
        return requirementTypeId;
    }

    public void setRequirementTypeId(int requirementTypeId) {
        this.requirementTypeId = requirementTypeId;
    }

    @Basic
    @Column(name = "name")
    @NotBlank
    @Size(min = 1, max = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "requirementType")
    public Collection<RequirementsEntity> getRequirements() {
        return requirements;
    }

    public void setRequirements(Collection<RequirementsEntity> requirements) {
        this.requirements = requirements;
    }
}
