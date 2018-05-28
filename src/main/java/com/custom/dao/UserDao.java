package com.custom.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.custom.entity.User;
import java.lang.String;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
