package com.spring.QuizApp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private Long id;
    private String questionContent;
    private Set<AnswerDto> answers;
}
