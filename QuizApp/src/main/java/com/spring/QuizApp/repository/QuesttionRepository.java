package com.spring.QuizApp.repository;

import com.spring.QuizApp.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuesttionRepository extends JpaRepository<Questions ,Long> {
    Questions findByQuestionContent(String questionContent);
}