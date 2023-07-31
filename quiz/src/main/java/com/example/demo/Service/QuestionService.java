package com.example.demo.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.QuestionDAO;
import com.example.demo.DTO.QuestionDTO;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

    @Autowired
    QuestionDAO questionDAO;

    public List<QuestionDTO> getAllQuestions() {
        try{
            return QuestionDTO.ToDTO(questionDAO.findAll());
        }        
        catch(Exception e)
        {
            logger.error("An error occurred", e);
        }
        return null;
    }

    public List<QuestionDTO> getQuestionsByCategory(String category) {
        try{
            return QuestionDTO.ToDTO(questionDAO.findByCategory(category));
        }        
        catch(Exception e)
        {
            logger.error("An error occurred", e);
        }
        return null;
    }

    public String addQuestion(QuestionDTO questionDTO) {
        try {
            questionDAO.save(QuestionDTO.toEntity(questionDTO));
            return "QUESTION ADDED";
        }        
        catch(Exception e)
        {
            logger.error("An error occurred", e);
        }
        return null;
    }

    public String updateQuestion(Integer id, QuestionDTO updatedQuestionDTO) {
        try {
            Optional<QuestionDTO> modifiedQuestionOptional = QuestionDTO.toDTO(questionDAO.findById(id));

            if (modifiedQuestionOptional.isPresent()) {
                QuestionDTO modifiedQuestionDTO = modifiedQuestionOptional.get();

                modifiedQuestionDTO.setId(id);
                modifiedQuestionDTO.setDifficultyLevel(updatedQuestionDTO.getDifficultyLevel());
                modifiedQuestionDTO.setCategory(updatedQuestionDTO.getCategory());
                modifiedQuestionDTO.setOption1(updatedQuestionDTO.getOption1());
                modifiedQuestionDTO.setOption2(updatedQuestionDTO.getOption2());
                modifiedQuestionDTO.setOption3(updatedQuestionDTO.getOption3());
                modifiedQuestionDTO.setOption4(updatedQuestionDTO.getOption4());
                modifiedQuestionDTO.setQuestionTitle(updatedQuestionDTO.getQuestionTitle());
                modifiedQuestionDTO.setRightAnswer(updatedQuestionDTO.getRightAnswer());

                questionDAO.save(QuestionDTO.toEntity(modifiedQuestionDTO));

                return "QUESTION UPDATED SUCCESSFULLY";
            } else {
                return "QUESTION NOT FOUND";
            }
        } catch (Exception e) {
            logger.error("An error occurred", e);
        }
        return "QUESTION NOT UPDATED";
    }

    public String deleteQuestion(Integer id) {
        try {
            Optional<QuestionDTO> OptionalDeletedQuestion = QuestionDTO.toDTO(questionDAO.findById(id));
            if (OptionalDeletedQuestion.isPresent()) {
                questionDAO.deleteById(id);
                return "QUESTION DELETED";
            } else {
                return "QUESTION NOT FOUND";
            }
        } catch (Exception e) {
            logger.error("An error occured", e);
        }
        return null;
    }
    
    public List<QuestionDTO> getQuestionsByOption2(String option2) {
        try {
            return QuestionDTO.ToDTO(questionDAO.searchQuestionsByOption2(option2));
        } catch (Exception e) {
            logger.error("An error occured", e);
        }
        return null;
    }

    public List<QuestionDTO> getQuestionsByIdGreaterThan(Integer n) {
        try {
            return QuestionDTO.ToDTO(questionDAO.findByIdGreaterThan(n));
        } catch (Exception e) {
            logger.error("An error occured", e);
        }
        return null;
    }

    public List<QuestionDTO> getQuestionsByDifficultyLevel(String difficultyLevel) {
        try {
            return questionDAO.findQuestionsByDifficultyLevel(difficultyLevel);
        } catch (Exception e) {
            logger.error("An error occured", e);
        }
        return null;
    }

    
}
