package com.example.demo.repository;

import com.example.demo.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {

    UsersEntity findByUserName (String userName);

  //  @Query("select u from UsersEntity u where u.email = ?1")
    UsersEntity findByEmail (String email);

    List<UsersEntity> findAllByUserNameNotLike(String name);
}
