package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Controller.QuestionController;
import com.example.demo.DAO.QuestionDAO;
import com.example.demo.DAO.QuizDAO;
import com.example.demo.DTO.QuestionDTO;
import com.example.demo.DTO.QuizDTO;

@Service
public class QuizService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
    
    @Autowired
    QuestionDAO questionDAO;

    @Autowired
    QuizDAO quizDAO;

    public String createQuiz(String category, int nbQuestions, String title) {
        
        try {
            List<QuestionDTO> questionsDTO = QuestionDTO.ToDTO(questionDAO.findRandomQuestionsByCategory(category, nbQuestions));

            QuizDTO quizDTO = new QuizDTO();
            quizDTO.setTitle(title);
            quizDTO.setQuestionsDTO(questionsDTO);

            quizDAO.save(QuizDTO.toEntity(quizDTO));

            return "SUCCESS";   
        } catch (Exception e) {
            logger.error("An error occurred", e);
        }
        return "FAILURE";
    }

    public Optional<QuizDTO> getQuiz(Integer id) {
        try {
            return QuizDTO.toDTO(quizDAO.findById(id));   
        } catch (Exception e) {
            logger.error("An error occurred", e);
        }
        return null;
    }

}
