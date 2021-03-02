package com.example.demo.service;

import com.example.demo.model.UsersEntity;
import com.example.demo.repository.UsersRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UsersService {

    private final UsersRepository repo;

    public UsersService(UsersRepository repo) {
        this.repo = repo;
    }

    public UsersEntity find(int id) {
        return repo.getOne(id);
    }

    public void save(UsersEntity user) {
        repo.save(user);
    }

    public UsersEntity find(String userName) {
        return repo.findByUserName(userName);
    }

    public UsersEntity findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public List<UsersEntity> findAllWithDifferentName(String name) {
         return repo.findAllByUserNameNotLike(name);
    }
}
