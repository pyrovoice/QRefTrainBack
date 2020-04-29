package com.quidditchreftraining.qreftrain.service;


import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Transactional
@Service(value = "questionService")
public class QuestionService {

    public void loadFromGoogleSheet() {

        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new StringReader(clientSecret));

        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);

        // build Credential object

        return credential;
    }
}
