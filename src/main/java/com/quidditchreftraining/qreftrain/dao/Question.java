package com.quidditchreftraining.qreftrain.dao;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Collection;


@Data
@Entity
@Table(name = "question")
@Getter
@Setter
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue
    private int id;
    private String publicId;
    @Enumerated
    private QuestionSubject questionSubject;
    private String URLVideo;
    private String questionText;
    private String answerExplanation;
    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<NationalGoverningBody> ValidNGBs;
    private boolean isRetired;
    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<Answer> answers;

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Question)) {
            return false;
        }
        Question question = (Question) o;
        for(Answer a: answers){
            if(!question.getAnswers().contains(a)){
                return false;
            }
        }
        return new EqualsBuilder()
                .append(publicId, question.publicId)
                .append(isRetired, question.isRetired)
                .append(answerExplanation, question.answerExplanation)
                .append(ValidNGBs, question.ValidNGBs)
                .append(questionSubject, question.questionSubject)
                .append(questionText, question.questionText)
                .append(URLVideo, question.URLVideo)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(publicId)
                .append(isRetired)
                .append(answerExplanation)
                .append(answers)
                .append(ValidNGBs)
                .append(questionSubject)
                .append(questionText)
                .append(URLVideo)
                .toHashCode();
    }
}
