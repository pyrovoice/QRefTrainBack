package com.quidditchreftraining.qreftrain.repository;

import com.quidditchreftraining.qreftrain.dao.Question;
import com.quidditchreftraining.qreftrain.dao.QuestionSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByQuestionSubjectIn(List<QuestionSubject> subjects);
}
