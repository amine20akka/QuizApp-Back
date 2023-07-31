package com.example.demo.Controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.QuizDTO;
import com.example.demo.Service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int nbQuestions, @RequestParam String title) 
    {
        try {
            return new ResponseEntity<>(quizService.createQuiz(category, nbQuestions, title),HttpStatus.CREATED);   
        } catch (Exception e) {
            logger.error("An error occurred", e);
        }
        return new ResponseEntity<String>("FAILURE", HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Optional<QuizDTO>> getQuiz(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(quizService.getQuiz(id), HttpStatus.OK);   
        } catch (Exception e) {
            logger.error("An error occurred", e);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    
}
