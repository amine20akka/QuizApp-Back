package com.example.demo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.QuestionDTO;
import com.example.demo.Service.QuestionService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    QuestionService questionService;

    /**
     * @return la liste de toutes les questions avec la réponse à la requête <code> HttpStatus.OK </code> si les traitements sont correctement exécutés, <code> HttpStatus.BAD_REQUEST </code> sinon
     */
    @GetMapping("/AllQuestions")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(questionService.getAllQuestions());   
        } catch (Exception e) {
            logger.error("An error occurred : ", e);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param category catégorie de la question à récupérer
     */
    @GetMapping("/category/{cat}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByCategory(@PathVariable("cat") String category) {
        try {
            return new ResponseEntity<>(questionService.getQuestionsByCategory(category), HttpStatus.OK);   
        } catch (Exception e) {
            logger.error("An error occurred : ", e);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param question la question à ajouter
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> addQuestion(@RequestBody QuestionDTO questionAdded) {
        try {
            return new ResponseEntity<>(questionService.addQuestion(questionAdded), HttpStatus.OK);   
        } catch (Exception e) {
            logger.error("An error occurred : ", e);
        }
        return new ResponseEntity<>("Question NOT added !", HttpStatus.BAD_REQUEST);
    }
        
    /**
     * @param id l'id de la question à modifier
     * @param updatedQuestionDTO la nouvelle question avec les modifications
     */
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updateQuestion(@PathVariable Integer id, @RequestBody QuestionDTO updatedQuestionDTO) {
        try {
            return new ResponseEntity<>(questionService.updateQuestion(id,updatedQuestionDTO), HttpStatus.OK);   
        } catch (Exception e) {
            logger.error("An error occurred : ", e);
        }
        return new ResponseEntity<>("Question NOT updated", HttpStatus.BAD_REQUEST);
    }

    /**
     * @param id l'id de la question à supprimer
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(questionService.deleteQuestion(id), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred : ", e);
        }
        return new ResponseEntity<>("Question NOT deleted", HttpStatus.BAD_REQUEST);
    }
    

    /**
     * @param option2 l'option2 désiré
     */
    @GetMapping("/option2/{opt}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByOption2(@PathVariable("opt") String option2) {
        try {
            return new ResponseEntity<>(questionService.getQuestionsByOption2(option2), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred : ", e);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    
    /**
     * @param n l'entier inférieur strictement à l'id des questions à récupérer
     */
    @GetMapping("/id>{int}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByIdGreaterThan(@PathVariable("int") Integer n) {
        try {
            return new ResponseEntity<>(questionService.getQuestionsByIdGreaterThan(n), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred : ", e);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/difficultyLevel/{diff}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByDifficultyLevel(@PathVariable("diff") String difficultyLevel) {
        try {
            return new ResponseEntity<>(questionService.getQuestionsByDifficultyLevel(difficultyLevel), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred : ", e);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}
