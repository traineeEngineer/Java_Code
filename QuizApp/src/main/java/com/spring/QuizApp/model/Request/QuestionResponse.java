package com.spring.QuizApp.model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {

    private String Id;
    private String questionContent;
    private Set<AnswerResponse> answerResponses;
}
