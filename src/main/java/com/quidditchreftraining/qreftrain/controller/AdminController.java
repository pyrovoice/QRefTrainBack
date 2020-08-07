package com.quidditchreftraining.qreftrain.controller;

import com.quidditchreftraining.qreftrain.dao.Question;
import com.quidditchreftraining.qreftrain.dto.Admin.ImportResult;
import com.quidditchreftraining.qreftrain.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {

    QuestionService questionService;


    @GetMapping(path = "admin/loadfromdrive", headers = "Accept=application/json")
    public @ResponseBody
    ImportResult loadQuestionsFromGoogleDrive() throws Exception {
            List<Question> importedQuestions = questionService.loadFromGoogleSheet();
            ImportResult importResult = questionService.updateQuestions(importedQuestions);
            return importResult;
    }
}
