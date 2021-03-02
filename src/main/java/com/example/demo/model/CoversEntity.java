package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "covers", schema = "game_shop")
public class CoversEntity {
    private int coverId;
    private String link;
    private GamesEntity game;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cover_id")
    public int getCoverId() {
        return coverId;
    }

    public void setCoverId(int coverId) {
        this.coverId = coverId;
    }

    @Basic
    @Column(name = "link")
    @NotBlank
    @Size(min = 1, max = 300)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "game_id", referencedColumnName = "game_id", nullable = false)
    public GamesEntity getGame() {
        return game;
    }

    public void setGame(GamesEntity game) {
        this.game = game;
    }
}
