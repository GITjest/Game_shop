package com.example.demo.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "games", schema = "game_shop")
public class GamesEntity {
    private int gameId;
    private String title;
    private String description;
    private Date releaseDate;
    private double price;
    private List<CategoriesEntity> categories;
    private DiscountsEntity discount;
    private Collection<OrdersEntity> orders;
    private List<RequirementsEntity> requirements;
    private Collection<ReviewsEntity> reviews;
    private List<PhotosEntity> photos;
    private CoversEntity cover;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Basic
    @Column(name = "title")
    @NotEmpty
    @Size(min = 1, max = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description")
    @NotEmpty
    @Size(min = 1, max = 10000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "release_date")
    @NotNull
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Basic
    @Column(name = "price")
    @NotNull
    @Min(0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToMany
    @JoinTable(name = "games_categories",
            joinColumns = { @JoinColumn(name = "game_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") })
    @NotEmpty
    @Size(min = 1)
    public List<CategoriesEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesEntity> categories) {
        this.categories = categories;
    }

    @OneToOne(mappedBy = "game", cascade = {CascadeType.ALL})
    public DiscountsEntity getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountsEntity discount) {
        this.discount = discount;
    }

    @OneToMany(mappedBy = "game")
    public Collection<OrdersEntity> getOrders() {
        return orders;
    }

    public void setOrders(Collection<OrdersEntity> orders) {
        this.orders = orders;
    }

    @OneToMany(mappedBy = "game", cascade = {CascadeType.ALL})
    @Valid
    public List<RequirementsEntity> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<RequirementsEntity> requirements) {
        this.requirements = requirements;
    }

    @OneToMany(mappedBy = "game", cascade = {CascadeType.ALL})
    public Collection<ReviewsEntity> getReviews() {
        return reviews;
    }

    public void setReviews(Collection<ReviewsEntity> reviews) {
        this.reviews = reviews;
    }

    @OneToMany(mappedBy = "game", cascade = {CascadeType.ALL})
    @Valid
    public List<PhotosEntity> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotosEntity> photos) {
        this.photos = photos;
    }

    @OneToOne(mappedBy = "game", cascade = {CascadeType.ALL})
    @Valid
    public CoversEntity getCover() {
        return cover;
    }

    public void setCover(CoversEntity cover) {
        this.cover = cover;
    }
}
