package net.contestmicroservice.Service.implementations;

import lombok.RequiredArgsConstructor;
import net.company.common.Exception.ResourceNotFoundException;
import net.contestmicroservice.Mapper.QuestionMapper;
import net.contestmicroservice.Repo.ContestRepo;
import net.contestmicroservice.Repo.QuestionRepo;
import net.contestmicroservice.Service.interfaces.ContestQuestionService;
import net.contestmicroservice.dto.request.CreateQuestionRequest;
import net.contestmicroservice.dto.response.QuestionResponse;
import net.contestmicroservice.entity.Contest;
import net.contestmicroservice.entity.ContestOption;
import net.contestmicroservice.entity.ContestQuestion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContestQuestionServiceImpl implements ContestQuestionService {

    private final QuestionRepo questionRepository;
    private final ContestRepo contestRepository;
    private final QuestionMapper questionMapper;

    @Override
    public QuestionResponse addQuestion(Long contestId,
                                        CreateQuestionRequest request) {

        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contest not found"));

        ContestQuestion question = ContestQuestion.builder()
                .contest(contest)
                .questionText(request.getQuestionText())
                .questionType(request.getQuestionType())
                .marks(request.getMarks())
                .explanation(request.getExplanation())
                .orderIndex(request.getOrderIndex())
                .build();

        List<ContestOption> options = request.getOptions()
                .stream()
                .map(optionRequest ->
                        ContestOption.builder()
                                .question(question)
                                .optionText(optionRequest.getOptionText())
                                .isCorrect(optionRequest.getIsCorrect())
                                .build()
                )
                .toList();

        question.getOptions().addAll(options);

        ContestQuestion savedQuestion =
                questionRepository.save(question);

        return questionMapper.toResponse(savedQuestion);
    }


    @Override
    public List<QuestionResponse> getQuestions(Long contestId) {

        List<ContestQuestion> questions =
                questionRepository.findByContestIdOrderByOrderIndex(contestId);

        return questions.stream()
                .map(questionMapper::toResponse)
                .toList();
    }


    @Override
    public QuestionResponse updateQuestion(Long questionId,
                                           CreateQuestionRequest request) {

        ContestQuestion question = questionRepository.findById(questionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question not found"));

        question.setQuestionText(request.getQuestionText());
        question.setQuestionType(request.getQuestionType());
        question.setMarks(request.getMarks());
        question.setExplanation(request.getExplanation());
        question.setOrderIndex(request.getOrderIndex());


        question.getOptions().clear();


        List<ContestOption> newOptions = request.getOptions()
                .stream()
                .map(optionRequest ->
                        ContestOption.builder()
                                .question(question)
                                .optionText(optionRequest.getOptionText())
                                .isCorrect(optionRequest.getIsCorrect())
                                .build()
                )
                .toList();

        question.getOptions().addAll(newOptions);

        ContestQuestion updatedQuestion =
                questionRepository.save(question);

        return questionMapper.toResponse(updatedQuestion);
    }


    @Override
    public void deleteQuestion(Long questionId) {

        ContestQuestion question = questionRepository.findById(questionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question not found"));

        questionRepository.delete(question);

    }
}
