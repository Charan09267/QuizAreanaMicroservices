package net.contestmicroservice.Mapper;

import net.contestmicroservice.dto.response.OptionResponse;
import net.contestmicroservice.dto.response.QuestionResponse;
import net.contestmicroservice.entity.ContestQuestion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionMapper {

    public QuestionResponse toResponse(ContestQuestion question) {

        List<OptionResponse> options =
                question.getOptions()
                        .stream()
                        .map(option -> OptionResponse.builder()
                                .id(option.getId())
                                .optionText(option.getOptionText())
                                .build())
                        .toList();

        return QuestionResponse.builder()
                .id(question.getId())
                .questionText(question.getQuestionText())
                .questionType(question.getQuestionType())
                .marks(question.getMarks())
                .explanation(question.getExplanation())
                .orderIndex(question.getOrderIndex())
                .options(options)
                .build();
    }
}
