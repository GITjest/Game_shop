package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "requirements", schema = "game_shop")
public class RequirementsEntity {
    private int requirementId;
    private RequirementTypesEntity requirementType;
    private String name;
    private GamesEntity game;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requirement_id")
    public int getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(int requirementId) {
        this.requirementId = requirementId;
    }

    @ManyToOne
    @JoinColumn(name = "requirement_type_id", referencedColumnName = "requirement_type_id", nullable = false)
    @NotNull
    public RequirementTypesEntity getRequirementType() {
        return requirementType;
    }

    public void setRequirementType(RequirementTypesEntity requirementType) {
        this.requirementType = requirementType;
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

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "game_id", nullable = false)
    public GamesEntity getGame() {
        return game;
    }

    public void setGame(GamesEntity game) {
        this.game = game;
    }
}
