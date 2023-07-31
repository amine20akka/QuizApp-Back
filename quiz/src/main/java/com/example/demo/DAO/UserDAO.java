package com.example.demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.User;


@Repository
public interface UserDAO extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
