package com.example.demo.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Question;

@Repository
public interface QuestionDAOCustom {

    public List<Question> searchQuestionsByOption2(String option2);

}
