package com.quidditchreftraining.qreftrain.controller;

import com.quidditchreftraining.qreftrain.dao.Question;
import com.quidditchreftraining.qreftrain.repository.QuestionRepository;
import com.quidditchreftraining.qreftrain.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Console;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {

    QuestionService questionService;

    @Autowired
    QuestionRepository questionRepository;


    @GetMapping(path = "admin/loadfromdrive", headers = "Accept=application/json")
    public @ResponseBody Iterable<Question> loadQuestionsFromGoogleDrive() throws Exception {
            List<Question> importedQuestions = questionService.loadFromGoogleSheet();
            questionRepository.saveAll(importedQuestions);
            return importedQuestions;
    }
}
