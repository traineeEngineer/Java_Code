package com.spring.QuizApp.controller;

import com.spring.QuizApp.dto.AnswerDto;
import com.spring.QuizApp.dto.QuestionDto;
import com.spring.QuizApp.model.Request.AnswerModel;
import com.spring.QuizApp.model.Request.QuestionModel;
import com.spring.QuizApp.model.Request.QuestionResponse;
import com.spring.QuizApp.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    private QuestionService service;

    private ModelMapper mapper=new ModelMapper();

    @PostMapping("/add")
    public QuestionResponse createQuestion(@RequestBody QuestionModel question) throws Exception {
        QuestionDto qDto=mapper.map(question, QuestionDto.class);
        return  mapper.map(service.createQuestions(qDto),  QuestionResponse.class);
    }

    @GetMapping("/{id}")
    public QuestionResponse getQuestionById(@PathVariable Long id){
        return  mapper.map(service.getQuestionById(id), QuestionResponse.class);
    }

    @GetMapping("/")
    public Set<QuestionResponse> getAllQuestions(){
        Set<QuestionResponse> result=new HashSet<>();
        for(QuestionDto dto:service.getAllQuestions()){
            QuestionResponse qResponse=mapper.map(dto, QuestionResponse.class);
            result.add(qResponse);
        }
        return  result;
    }

    @DeleteMapping("/{id}")
    public  String deleteQuestionById(@PathVariable Long id){
        service.deleteQuestionById(id);
        return "Question was deleted successfully";
    }

    @PutMapping("/{id}")
    public QuestionResponse addAnswerToQuestion(@PathVariable Long id, @RequestBody AnswerModel modelAnswer){
        AnswerDto answerDtoAdd=mapper.map(modelAnswer, AnswerDto.class);
        return  mapper.map(service.updateAnswer(answerDtoAdd, id), QuestionResponse.class);
    }

}
