package com.custom.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.custom.entity.User;


public interface UserService extends UserDetailsService{
	User findByUsername(String username);

    List<User> findAll();
    
    void save(User user);
}
