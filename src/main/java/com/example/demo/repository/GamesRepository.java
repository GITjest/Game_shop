package com.example.demo.repository;

import com.example.demo.model.CategoriesEntity;
import com.example.demo.model.GamesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface GamesRepository extends JpaRepository<GamesEntity, Integer> {

    List<GamesEntity> findByCategoriesAndTitleStartingWithAndPriceGreaterThanAndPriceLessThanAndReleaseDateAfterAndReleaseDateBefore(CategoriesEntity category, String title, double priceMin, double priceMax, Date dateMin, Date dateMax);

    List<GamesEntity> findByTitleStartingWithAndPriceGreaterThanAndPriceLessThanAndReleaseDateAfterAndReleaseDateBefore(String title, double priceMin, double priceMax, Date dateMin, Date dateMax);

}
