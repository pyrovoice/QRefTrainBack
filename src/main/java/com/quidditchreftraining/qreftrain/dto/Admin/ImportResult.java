package com.quidditchreftraining.qreftrain.dto.Admin;

import com.quidditchreftraining.qreftrain.dao.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ImportResult {
    private List<Question> questionsAdded = new ArrayList<>();
    private List<Question> questionsRemoved = new ArrayList<>();
    private List<Question> questionsUntouched = new ArrayList<>();
}
