package net.contestmicroservice.Service.interfaces;

import net.contestmicroservice.dto.request.CreateQuestionRequest;
import net.contestmicroservice.dto.response.QuestionResponse;

import java.util.List;

public interface ContestQuestionService {
    QuestionResponse addQuestion(
            Long contestId,
            CreateQuestionRequest request);

    List<QuestionResponse> getQuestions(
            Long contestId);

    QuestionResponse updateQuestion(
            Long questionId,
            CreateQuestionRequest request);

    void deleteQuestion(Long questionId);

}
