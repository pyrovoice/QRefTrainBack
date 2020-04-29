package com.quidditchreftraining.qreftrain.dao;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue
    private int id;
    private String text;
    private boolean isGoodAnswer;
}
