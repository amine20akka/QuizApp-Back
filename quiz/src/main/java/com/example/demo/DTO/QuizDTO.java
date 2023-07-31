package com.example.demo.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.Question;
import com.example.demo.Entity.Quiz;
import lombok.Data;

@Data
public class QuizDTO {
    
    private Integer id;
    private String title;
    private List<QuestionDTO> questionsDTO;

    /*public static QuizDTO toDTO(Quiz quiz) {
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setId(quiz.getId());
        quizDTO.setTitle(quiz.getTitle());

        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for (Question question : quiz.getQuestions()) {
            QuestionDTO questionDTO = QuestionDTO.toDTO(question);
            questionDTOs.add(questionDTO);
        }
        quizDTO.setQuestionsDTO(questionDTOs);

        return quizDTO;
    }*/

    public static Quiz toEntity(QuizDTO quizDTO) {
        Quiz quiz = new Quiz();
        quiz.setId(quizDTO.getId());
        quiz.setTitle(quizDTO.getTitle());

        // Convert the list of QuestionDTOs to Question entities
        List<Question> questionEntities = new ArrayList<>();
        for (QuestionDTO questionDTO : quizDTO.getQuestionsDTO()) {
            Question question = QuestionDTO.toEntity(questionDTO);
            questionEntities.add(question);
        }
        quiz.setQuestions(questionEntities);

        return quiz;
    }

    public static Optional<QuizDTO> toDTO(Optional<Quiz> quizOptional) {
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            QuizDTO quizDTO = new QuizDTO();
            quizDTO.setId(quiz.getId());
            quizDTO.setTitle(quiz.getTitle());

            List<QuestionDTO> questionDTOs = new ArrayList<>();
            for (Question question : quiz.getQuestions()) {
                QuestionDTO questionDTO = QuestionDTO.toDTO(question);
                questionDTOs.add(questionDTO);
            }
            quizDTO.setQuestionsDTO(questionDTOs);

            return Optional.of(quizDTO);
        }

        return Optional.empty();
    }

}
