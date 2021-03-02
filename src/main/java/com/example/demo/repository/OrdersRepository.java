package com.example.demo.repository;

import com.example.demo.model.OrdersEntity;
import com.example.demo.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer> {

    void deleteByUser(UsersEntity usersEntity);
}
