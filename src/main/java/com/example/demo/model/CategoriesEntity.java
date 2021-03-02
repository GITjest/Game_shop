package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
@Table(name = "categories", schema = "game_shop")
public class CategoriesEntity {
    private int categoryId;
    private String title;
    private Collection<GamesEntity> games;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "title")
    @NotEmpty
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToMany(mappedBy = "categories")
    public Collection<GamesEntity> getGames() {
        return games;
    }

    public void setGames(Collection<GamesEntity> games) {
        this.games = games;
    }
}
