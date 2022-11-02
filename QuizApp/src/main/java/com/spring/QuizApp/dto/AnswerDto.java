package com.spring.QuizApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {

    private long Id;
    private String answerContent;
    private boolean correct;
    private QuestionDto question;
}
