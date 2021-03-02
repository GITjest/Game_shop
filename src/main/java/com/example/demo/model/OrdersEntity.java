package com.example.demo.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "orders", schema = "game_shop")
public class OrdersEntity {
    private int orderId;
    private Timestamp endDate;
    private GamesEntity game;
    private UsersEntity user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "end_date")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "game_id", nullable = false)
    public GamesEntity getGame() {
        return game;
    }

    public void setGame(GamesEntity game) {
        this.game = game;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }
}
