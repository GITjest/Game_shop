package com.example.demo.repository;

import com.example.demo.model.DiscountsEntity;
import com.example.demo.model.GamesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountsRepository extends JpaRepository<DiscountsEntity, Integer> {

    DiscountsEntity findByGame(GamesEntity gamesEntity);

}
