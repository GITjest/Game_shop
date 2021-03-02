package com.example.demo.service;

import com.example.demo.model.CategoriesEntity;
import com.example.demo.model.GamesEntity;
import com.example.demo.repository.GamesRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class GamesService {
    private final GamesRepository repo;

    public GamesService(GamesRepository repo) {
        this.repo = repo;
    }

    public GamesEntity getOne(int id) {
        return repo.getOne(id);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public GamesEntity save(GamesEntity game) {
        return repo.save(game);
    }

    public List<GamesEntity> findAll() {
        return repo.findAll();
    }

    public List<GamesEntity> findGames(CategoriesEntity category, String title, double priceMin, double priceMax, Date dateMin, Date dateMax) {
        return repo.findByCategoriesAndTitleStartingWithAndPriceGreaterThanAndPriceLessThanAndReleaseDateAfterAndReleaseDateBefore(category, title, priceMin, priceMax, dateMin, dateMax);
    }

    public List<GamesEntity> findGames(String title, double priceMin, double priceMax, Date dateMin, Date dateMax) {
        return repo.findByTitleStartingWithAndPriceGreaterThanAndPriceLessThanAndReleaseDateAfterAndReleaseDateBefore(title, priceMin, priceMax, dateMin, dateMax);
    }

}
