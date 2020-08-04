package com.quidditchreftraining.qreftrain.repository;

import com.quidditchreftraining.qreftrain.dao.Question;
import com.quidditchreftraining.qreftrain.dao.QuestionSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByQuestionSubjectIn(List<QuestionSubject> subjects);
}
