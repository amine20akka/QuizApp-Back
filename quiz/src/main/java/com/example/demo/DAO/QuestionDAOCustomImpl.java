package com.example.demo.DAO;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Entity.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuestionDAOCustomImpl implements QuestionDAOCustom {

    private final EntityManager entityManager;

    @Override
    public List<Question> searchQuestionsByOption2(String option2) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Question> query = criteriaBuilder.createQuery(Question.class);
        Root<Question> root = query.from(Question.class);

        List<Predicate> predicates = new ArrayList<>();
        if (option2 != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("option2")),
                    "%" + option2.toLowerCase() + "%"));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
