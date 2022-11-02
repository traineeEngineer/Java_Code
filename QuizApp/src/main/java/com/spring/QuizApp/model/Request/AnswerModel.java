package com.spring.QuizApp.model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerModel {

    private String answerContent;
    private boolean correct;
}
