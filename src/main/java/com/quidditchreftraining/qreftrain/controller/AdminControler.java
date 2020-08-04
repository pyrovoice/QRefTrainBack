package com.quidditchreftraining.qreftrain.controller;

import com.quidditchreftraining.qreftrain.dao.QuestionSubject;
import com.quidditchreftraining.qreftrain.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Controller
@AllArgsConstructor
public class AdminControler {

    QuestionService questionService;

    @ResponseBody
    @PostMapping(path = "admin/loadfromdrive")
    public boolean loadQuestionsFromGoogleDrive() throws IOException, GeneralSecurityException {
        try {
            return questionService.loadFromGoogleSheet();
        }catch(Exception e){
            return false;
        }
    }
}
