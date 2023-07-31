package com.example.demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Quiz;

@Repository
public interface QuizDAO extends JpaRepository<Quiz, Integer> {

}
