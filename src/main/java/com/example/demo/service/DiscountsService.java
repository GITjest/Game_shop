package com.example.demo.service;

import com.example.demo.model.DiscountsEntity;
import com.example.demo.model.GamesEntity;
import com.example.demo.repository.DiscountsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DiscountsService {
    private final DiscountsRepository repo;

    public DiscountsService(DiscountsRepository repo) {
        this.repo = repo;
    }

    public void save(DiscountsEntity discountsEntity) {
        repo.save(discountsEntity);
    }

    public DiscountsEntity getOne(int id) {
        return repo.getOne(id);
    }

    public DiscountsEntity getOne(GamesEntity gamesEntity) {
        return repo.findByGame(gamesEntity);
    }
}
