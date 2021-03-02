package com.example.demo.service;

import com.example.demo.model.OrdersEntity;
import com.example.demo.model.UsersEntity;
import com.example.demo.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrdersService {
    private final OrdersRepository repo;

    public OrdersService(OrdersRepository repo) {
        this.repo = repo;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public void delete(UsersEntity usersEntity) {
        repo.deleteByUser(usersEntity);
    }

    public void save(OrdersEntity ordersEntity) {
        repo.save(ordersEntity);
    }
}
