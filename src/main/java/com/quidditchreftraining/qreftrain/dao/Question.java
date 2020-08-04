package com.quidditchreftraining.qreftrain.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;


@Data
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true, nullable = false)
    private String publicId;
    @Enumerated
    private QuestionSubject questionSubject;
    private String URLVideo;
    private String questionText;
    private String answerExplanation;
    @ManyToOne
    private NationalGoverningBody NGB;
    private boolean isRetired;
    @ManyToMany
    private Collection<Answer> answers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public QuestionSubject getQuestionSubject() {
        return questionSubject;
    }

    public void setQuestionSubject(QuestionSubject questionSubject) {
        this.questionSubject = questionSubject;
    }

    public String getURLVideo() {
        return URLVideo;
    }

    public void setURLVideo(String URLVideo) {
        this.URLVideo = URLVideo;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswerExplanation() {
        return answerExplanation;
    }

    public void setAnswerExplanation(String answerExplanation) {
        this.answerExplanation = answerExplanation;
    }

    public NationalGoverningBody getNGB() {
        return NGB;
    }

    public void setNGB(NationalGoverningBody NGB) {
        this.NGB = NGB;
    }

    public boolean isRetired() {
        return isRetired;
    }

    public void setRetired(boolean retired) {
        isRetired = retired;
    }

    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }
}
