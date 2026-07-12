package net.contestmicroservice.dto.request;

import lombok.Getter;
import lombok.Setter;
import net.contestmicroservice.entity.enums.QuestionType;

import java.util.List;

@Getter
@Setter
public class CreateQuestionRequest {


    private String questionText;


    private QuestionType questionType;


    private Integer marks;


    private String explanation;


    private Integer orderIndex;

    private List<CreateOptionRequest> options;
}
