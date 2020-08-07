package com.quidditchreftraining.qreftrain.dao;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Answer)) {
            return false;
        }
        Answer answer = (Answer) o;
        return new EqualsBuilder()
                .append(text, answer.text)
                .append(isGoodAnswer, answer.isGoodAnswer)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(text)
                .append(isGoodAnswer)
                .toHashCode();
    }
}
