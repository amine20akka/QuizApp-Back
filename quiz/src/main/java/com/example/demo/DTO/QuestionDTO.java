package com.example.demo.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.Question;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    private String difficultyLevel;
    private String category;

    public QuestionDTO() {}

    public QuestionDTO(Integer id, String difficultyLevel) {
        this.id = id;
        this.difficultyLevel = difficultyLevel;
    }

    public static QuestionDTO toDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setQuestionTitle(question.getQuestion_title());
        questionDTO.setOption1(question.getOption1());
        questionDTO.setOption2(question.getOption2());
        questionDTO.setOption3(question.getOption3());
        questionDTO.setOption4(question.getOption4());
        questionDTO.setRightAnswer(question.getRight_answer());
        questionDTO.setDifficultyLevel(question.getDifficultylevel());
        questionDTO.setCategory(question.getCategory());

        return questionDTO;
    }

    public static List<QuestionDTO> ToDTO(List<Question> questions) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setId(question.getId());
            questionDTO.setQuestionTitle(question.getQuestion_title());
            questionDTO.setOption1(question.getOption1());
            questionDTO.setOption2(question.getOption2());
            questionDTO.setOption3(question.getOption3());
            questionDTO.setOption4(question.getOption4());
            questionDTO.setRightAnswer(question.getRight_answer());
            questionDTO.setDifficultyLevel(question.getDifficultylevel());
            questionDTO.setCategory(question.getCategory());

            questionDTOList.add(questionDTO);
        }

        return questionDTOList;
    }
    
    public static Question toEntity(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setId(questionDTO.getId());
        question.setQuestion_title(questionDTO.getQuestionTitle());
        question.setOption1(questionDTO.getOption1());
        question.setOption2(questionDTO.getOption2());
        question.setOption3(questionDTO.getOption3());
        question.setOption4(questionDTO.getOption4());
        question.setRight_answer(questionDTO.getRightAnswer());
        question.setDifficultylevel(questionDTO.getDifficultyLevel());
        question.setCategory(questionDTO.getCategory());

        return question;
    }

    public static List<Question> toEntity(List<QuestionDTO> questions) {
        List<Question> questionList = new ArrayList<>();

        for (QuestionDTO questionDTO : questions) {
            Question question = new Question();
            question.setId(questionDTO.getId());
            question.setQuestion_title(questionDTO.getQuestionTitle());
            question.setOption1(questionDTO.getOption1());
            question.setOption2(questionDTO.getOption2());
            question.setOption3(questionDTO.getOption3());
            question.setOption4(questionDTO.getOption4());
            question.setRight_answer(questionDTO.getRightAnswer());
            question.setDifficultylevel(questionDTO.getDifficultyLevel());
            question.setCategory(questionDTO.getCategory());
            
            questionList.add(question);
        }

        return questionList;
    }

    public static Optional<QuestionDTO> toDTO(Optional<Question> questionOptional) {
        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setId(question.getId());
            questionDTO.setQuestionTitle(question.getQuestion_title());
            questionDTO.setOption1(question.getOption1());
            questionDTO.setOption2(question.getOption2());
            questionDTO.setOption3(question.getOption3());
            questionDTO.setOption4(question.getOption4());
            questionDTO.setRightAnswer(question.getRight_answer());
            questionDTO.setDifficultyLevel(question.getDifficultylevel());
            questionDTO.setCategory(question.getCategory());

            return Optional.of(questionDTO);
        }

        return Optional.empty();
    }


}
