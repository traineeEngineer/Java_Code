package com.spring.QuizApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ANSWERTAB")
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private String  answerContent;
    private boolean correct;
    @ManyToOne
    @JoinColumn(name="question_id")
    private Questions questions;


}
