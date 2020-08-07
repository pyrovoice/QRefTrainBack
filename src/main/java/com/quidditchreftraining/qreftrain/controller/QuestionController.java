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
public class QuestionController {

    QuestionService questionService;
    @Autowired
    QuestionRepository questionRepository;

    @ResponseBody
    @GetMapping(path = "question/getquestionsbysubjects")
    public List<Question> loadQuestionsBySubjects(@RequestParam(required=false) List<QuestionSubject> subjects) throws IOException, GeneralSecurityException {
        if(subjects == null || subjects.size() == 0){
            return this.loadAllQuestions();
        }
        return questionRepository.findByQuestionSubjectIn(subjects);
    }

    @ResponseBody
    @GetMapping(path = "question/getAllQuestions")
    public List<Question> loadAllQuestions() {
        List<Question> loadAll =  questionRepository.findAll();
        return loadAll;
    }
}
