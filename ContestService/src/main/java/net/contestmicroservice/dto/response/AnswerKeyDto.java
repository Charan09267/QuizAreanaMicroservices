package net.contestmicroservice.dto.response;

import lombok.*;
import net.contestmicroservice.entity.enums.QuestionType;

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
