package com.example.demo.service;

import com.example.demo.model.RolesEntity;
import com.example.demo.model.UsersEntity;
import com.example.demo.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsersServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersService usersService;

    @Test
    void savedUserHasRegistrationDate() {
        UsersEntity user = new UsersEntity("user", "password", "user@mail.com");
        when(usersRepository.save(any(UsersEntity.class))).then(returnsFirstArg());
        UsersEntity savedUser = usersService.registerUser(user, null);
        assertThat(savedUser.getRegistrationDate()).isNotNull();
    }

    @Test
    void savedUserHasRoleUser() {
        UsersEntity user = new UsersEntity("user", "password", "user@mail.com");
        RolesEntity role = new RolesEntity("USER");
        when(usersRepository.save(any(UsersEntity.class))).then(returnsFirstArg());
        UsersEntity savedUser = usersService.registerUser(user, role);
        assertThat(savedUser.getRole().getRole()).isEqualTo("USER");
    }
}
