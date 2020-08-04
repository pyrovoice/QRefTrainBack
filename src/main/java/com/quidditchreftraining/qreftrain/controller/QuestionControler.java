package com.quidditchreftraining.qreftrain.controller;

import com.quidditchreftraining.qreftrain.dao.Question;
import com.quidditchreftraining.qreftrain.dao.QuestionSubject;
import com.quidditchreftraining.qreftrain.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class QuestionControler {

    QuestionService questionService;

    @ResponseBody
    @PostMapping(path = "question/getquestionsbysubjects")
    public List<Question> loadQuestionsFromGoogleDrive(@RequestBody List<QuestionSubject> subjects) throws IOException, GeneralSecurityException {
       return new ArrayList<>();
    }

    @ResponseBody
    @GetMapping(path = "question/getAllQuestions")
    public List<Question> loadAllQuestions() {
        List<Question> loadAll =  questionService.getAllQuestions();
        return loadAll;
    }
}
