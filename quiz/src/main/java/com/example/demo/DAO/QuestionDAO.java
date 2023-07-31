package com.example.demo.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.DTO.QuestionDTO;
import com.example.demo.Entity.Question;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer>,QuestionDAOCustom {

    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM Question Q WHERE Q.category = :category ORDER BY RAND() LIMIT :nbQuestions", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, Integer nbQuestions);

    @Query("SELECT new com.example.demo.DTO.QuestionDTO(Q.id, Q.difficultylevel) FROM Question Q WHERE Q.difficultylevel=:difficultyLevel")
    List<QuestionDTO> findQuestionsByDifficultyLevel(String difficultyLevel);

    public List<Question> findByIdGreaterThan(Integer n);

}
