package net.company.evaluationservice.dto;

import lombok.*;
import net.company.evaluationservice.entity.QuestionType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerKeyDto {

    private Long questionId;

    private List<Long> correctOptionIds;

    private Integer marks;

    private QuestionType questionType;
}
