package com.quidditchreftraining.qreftrain.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Data
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true, nullable = false)
    private int publicId;
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

}
