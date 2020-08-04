package com.quidditchreftraining.qreftrain.service;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.quidditchreftraining.qreftrain.dao.Answer;
import com.quidditchreftraining.qreftrain.dao.Question;
import com.quidditchreftraining.qreftrain.dao.QuestionSubject;
import com.quidditchreftraining.qreftrain.repository.NationalGoverningBodyRepository;
import com.quidditchreftraining.qreftrain.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Transactional
@Service(value = "questionService")
public class QuestionService {

    @Autowired
    NationalGoverningBodyRepository nationalGoverningBodyRepository;
    @Autowired
    QuestionRepository questionRepository;
    private final static String SHEET_ID = "1CGK04DNT5Ym7Pxl7w7HzkqLrsNaLeNS0SkG2nzMNH8Y";

    public boolean loadFromGoogleSheet() throws Exception {
        Credential credential = authorize();
        Sheets driveSheets = new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName("QRefTrain")
                .build();
        List<Question> importedQuestions = new ArrayList();
        Sheets.Spreadsheets spreadsheets = driveSheets.spreadsheets();
        Spreadsheet readResult = spreadsheets.get(SHEET_ID).execute();
        for(Sheet sheet : readResult.getSheets()){
            String range = sheet.getProperties().getTitle() + "!A2:G";
            ValueRange values = spreadsheets.values().get(SHEET_ID, range).execute();
            if(values == null || values.size() < 1){
                throw new Exception("Error when accessing sheets");
            }
            for (List<Object> row : values.getValues())
            {
                Question newQuestion = new Question();
                List<Answer> answers = new ArrayList<Answer>();
                QuestionSubject questionSubject = Enum.valueOf(QuestionSubject.class, sheet.getProperties().getTitle());
                for (int i = 0; i < row.size(); i++)
                {
                    switch (i)
                    {
                        //Public ID
                        case 0:
                            newQuestion.setPublicId(questionSubject + "-" + row.get(i));
                            break;
                        // Question Text
                        case 1:
                            newQuestion.setQuestionText((String) row.get(i));
                            break;
                        //Good answers
                        case 2:
                            String[] allGoodAnswers = row.get(i).toString().split("\n" );
                            for (int goodAnswerCounter = 0; goodAnswerCounter < allGoodAnswers.length; goodAnswerCounter++)
                            {
                                if (!StringUtils.hasLength(allGoodAnswers[goodAnswerCounter]))
                                {
                                    answers.add(new Answer(allGoodAnswers[goodAnswerCounter], true));
                                }
                            }
                            break;
                        //Bad Answers
                        case 3:
                            String[] allBadAnswers = row.get(i).toString().split("\n");
                            for (int badAnswerCounter = 0; badAnswerCounter < allBadAnswers.length; badAnswerCounter++)
                            {
                                //When adding "Cards" to bad answers, it means that we want to offer the user all card penalty possible as bad answers, except the one that were already selected as good answers
                                //For each card color possible, we check that this answer does not already exists in the good answers and if not, we add it to the bad answers
                                if (allBadAnswers[badAnswerCounter].equals("Cards"))
                                {
                                    if (answers.stream().filter(u -> u.getText().equals("BlueCard")).findFirst().isEmpty())
                                    {
                                        answers.add(new Answer("BlueCard", false));
                                    }
                                    if (answers.stream().filter(u -> u.getText().equals("YellowCard")).findFirst().isEmpty())
                                    {
                                        answers.add(new Answer("YellowCard", false));
                                    }
                                    if (answers.stream().filter(u -> u.getText().equals("RedCard")).findFirst().isEmpty())
                                    {
                                        answers.add(new Answer("RedCard", false));
                                    }
                                }else if (StringUtils.hasLength(allBadAnswers[badAnswerCounter]))
                                {
                                    answers.add(new Answer(allBadAnswers[badAnswerCounter], false));
                                }
                            }
                            break;
                        //Answer explanation
                        case 4:
                            newQuestion.setAnswerExplanation(row.get(i).toString());
                            break;
                        //NGB
                        case 5:
                            newQuestion.setNGB(nationalGoverningBodyRepository.findOneByAbbreviation(row.get(i).toString()));
                            break;
                        //Link
                        case 6:
                            if (!StringUtils.hasLength((CharSequence) row.get(i)))
                            {
                                newQuestion.setURLVideo(row.get(i).toString());
                            }
                            break;
                    }
                }
                newQuestion.setAnswers(answers);
                newQuestion.setQuestionSubject(questionSubject);
                importedQuestions.add(newQuestion);
            }
        }
        for(Question question : importedQuestions){
            Question q = questionRepository.save(question);
            int a = 1;
        }
        return true;
    }

    private static Credential authorize() throws IOException, GeneralSecurityException {
        InputStream in = QuestionService.class.getResourceAsStream("/credentials.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new InputStreamReader(in));

        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), clientSecrets, scopes).setDataStoreFactory(new MemoryDataStoreFactory())
                .setAccessType("offline").build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");

        return credential;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
