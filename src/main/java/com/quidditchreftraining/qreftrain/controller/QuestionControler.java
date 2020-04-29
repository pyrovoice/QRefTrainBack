package com.quidditchreftraining.qreftrain.controller;

import com.quidditchreftraining.qreftrain.dao.QuestionSubject;
import com.quidditchreftraining.qreftrain.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
import java.util.List;

@Controller
@AllArgsConstructor
public class QuestionControler {

    QuestionService questionService;
    @ResponseBody
    @PostMapping(path = "question/loadfromdrive")
    public void loadQuestionsFromGoogleDrive(@RequestBody List<QuestionSubject> subjects) throws FileNotFoundException {
        questionService.loadFromGoogleSheet();

    }
}
