package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "game_shop")
public class UsersEntity {
    private int userId;
    private String userName;
    private String password;
    private Timestamp registrationDate;
    private String email;
    private RolesEntity role;
    private Collection<OrdersEntity> orders;
    private Collection<ReviewsEntity> reviews;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "password")
    @NotBlank
    @Size(min = 3, max = 200)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "registration_date")
    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Basic
    @Column(name = "email")
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    public RolesEntity getRole() {
        return role;
    }

    public void setRole(RolesEntity role) {
        this.role = role;
    }

    @Basic
    @Column(name = "user_name")
    @NotBlank
    @Size(min = 3, max = 25)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    public Collection<OrdersEntity> getOrders() {
        return orders;
    }

    public void setOrders(Collection<OrdersEntity> orders) {
        this.orders = orders;
    }

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    public Collection<ReviewsEntity> getReviews() {
        return reviews;
    }

    public void setReviews(Collection<ReviewsEntity> reviews) {
        this.reviews = reviews;
    }
}
