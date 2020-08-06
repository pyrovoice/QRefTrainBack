package com.quidditchreftraining.qreftrain.controller;

import com.quidditchreftraining.qreftrain.dao.Question;
import com.quidditchreftraining.qreftrain.dao.QuestionSubject;
import com.quidditchreftraining.qreftrain.repository.QuestionRepository;
import com.quidditchreftraining.qreftrain.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class QuestionControler {

    QuestionService questionService;
    @Autowired
    QuestionRepository questionRepository;

    @ResponseBody
    @GetMapping(path = "question/getquestionsbysubjects")
    public List<Question> loadQuestionsBySubjects(@RequestParam(required=false) QuestionSubject[] subjects) throws IOException, GeneralSecurityException {
        if(subjects == null || subjects.length == 0){
            return this.loadAllQuestions();
        }
        return questionRepository.findByQuestionSubjectIn(Arrays.asList(subjects));
    }

    @ResponseBody
    @GetMapping(path = "question/getAllQuestions")
    public List<Question> loadAllQuestions() {
        List<Question> loadAll =  questionRepository.findAll();
        return loadAll;
    }
}
