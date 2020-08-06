package com.quidditchreftraining.qreftrain.enumconverter;

import com.quidditchreftraining.qreftrain.dao.QuestionSubject;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;

public class StringToQuestionSubjectConverter implements Converter<String, QuestionSubject> {
    @Override
    public QuestionSubject convert(String source) {
        return QuestionSubject.valueOf(source.toUpperCase());
    }

    public Iterable<QuestionSubject> convertAll(String[] sources){
        ArrayList<QuestionSubject> list = new ArrayList<>();
        for (String source : sources) {
            list.add(this.convert(source));
        }
        return list;
    }
}
