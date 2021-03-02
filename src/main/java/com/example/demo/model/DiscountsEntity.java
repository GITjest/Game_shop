package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "discounts", schema = "game_shop")
public class DiscountsEntity {
    private int discountId;
    private int value;
    private Date startDate;
    private Date endDate;
    private GamesEntity game;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    @Basic
    @Column(name = "value")
    @NotNull
    @Min(0)
    @Max(100)
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Basic
    @Column(name = "start_date")
    @NotNull
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date")
    @NotNull
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @OneToOne
    @JoinColumn(name = "game_id", referencedColumnName = "game_id", nullable = false)
    public GamesEntity getGame() {
        return game;
    }

    public void setGame(GamesEntity game) {
        this.game = game;
    }
}
