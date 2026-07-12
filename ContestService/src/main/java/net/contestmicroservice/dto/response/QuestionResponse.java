package net.contestmicroservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.contestmicroservice.entity.enums.QuestionType;

import java.util.List;

@Getter
@Setter
@Builder
public class QuestionResponse {


    private Long id;

    private String questionText;

    private QuestionType questionType;

    private Integer marks;

    private String explanation;

    private Integer orderIndex;

    private List<OptionResponse> options;

}
