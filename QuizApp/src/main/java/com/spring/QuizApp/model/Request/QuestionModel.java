package com.spring.QuizApp.model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionModel {

    private String questionContent;
    private Set<AnswerModel> answers;

}
