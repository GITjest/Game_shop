package com.example.demo.service;

import com.example.demo.model.UsersEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersService userService;

    public UserDetailsServiceImpl(UsersService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UsersEntity user = userService.find(userName);
        if (user == null)  throw new UsernameNotFoundException("User not found.");

        User.UserBuilder builder = User.withUsername(userName);
        builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
        builder.roles(user.getRole().getRole());
        return builder.build();
    }
}
