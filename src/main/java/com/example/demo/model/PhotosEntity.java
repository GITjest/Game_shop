package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "photos", schema = "game_shop")
public class PhotosEntity {
    private int photoId;
    private String link;
    private GamesEntity game;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
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

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "game_id", nullable = false)
    public GamesEntity getGame() {
        return game;
    }

    public void setGame(GamesEntity game) {
        this.game = game;
    }
}
