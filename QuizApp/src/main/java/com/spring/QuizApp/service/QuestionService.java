package com.spring.QuizApp.service;

import com.spring.QuizApp.dto.AnswerDto;
import com.spring.QuizApp.dto.QuestionDto;
import com.spring.QuizApp.model.Questions;
import com.spring.QuizApp.repository.QuesttionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Service
public class QuestionService {

    @Autowired
    private QuesttionRepository qRepo;

    private ModelMapper mapper=new ModelMapper();

    public QuestionDto createQuestions(QuestionDto questionDto) throws Exception {
        if(qRepo.findByQuestionContent(questionDto.getQuestionContent())!=null){
            throw new Exception("Question already n database");
        }
        for (AnswerDto answerDto:questionDto.getAnswers()){
            answerDto.setQuestion(questionDto);
        }
        Questions questions=mapper.map(questionDto, Questions.class);
        QuestionDto returnValue=mapper.map(qRepo.save(questions), QuestionDto.class);
        return returnValue;
    }

    public  QuestionDto getQuestionById(Long id){
        return  mapper.map(qRepo.findById(id), QuestionDto.class);
    }

    public  String deleteQuestionById(Long id){
        qRepo.deleteById(id);
        return "Operation successfully";
    }


    public Set<QuestionDto> getAllQuestions() {
        Set<QuestionDto> returnSet=new HashSet<>();
        for(Questions q:qRepo.findAll()){
            QuestionDto questionDto= mapper.map(q, QuestionDto.class);
        }
        return returnSet;
    }

    public  QuestionDto updateAnswer(AnswerDto answerDto,Long id){
        Questions questions=qRepo.findById(id).get();
        QuestionDto questionDto=mapper.map(questions, QuestionDto.class);
        questionDto.getAnswers().add(answerDto);
        Questions returnQuestion=qRepo.save(mapper.map(questionDto, Questions.class));
        return mapper.map(returnQuestion, QuestionDto.class);
    }

}
