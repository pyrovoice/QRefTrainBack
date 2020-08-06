package com.quidditchreftraining.qreftrain.dao;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "answer")
@Getter
@Setter
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue
    private int id;
    private String text;
    private boolean isGoodAnswer;

    public Answer(String answerText, boolean isGoodAnswer) {
        this.text = answerText;
        this.isGoodAnswer = isGoodAnswer;
    }
}
