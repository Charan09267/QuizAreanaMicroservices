package net.ContestAttempMicroService.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import net.ContestAttempMicroService.Client.ContestServiceClient;
import net.ContestAttempMicroService.Mapper.AttemptMapper;
import net.ContestAttempMicroService.Repo.ContestAttemptRepository;
import net.ContestAttempMicroService.dto.AttemptResponse;
import net.ContestAttempMicroService.dto.ContestDto;
import net.ContestAttempMicroService.dto.ContestResultResponse;
import net.ContestAttempMicroService.entity.AttemptStatus;
import net.ContestAttempMicroService.entity.ContestAttempt;
import net.ContestAttempMicroService.entity.ParticipantStatus;
import net.company.common.Exception.ContestNotFoundException;
import net.company.common.Exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContestAttemptServiceImpl implements ContestAttemptService {
    private final ContestServiceClient contestServiceClient;
    private final ContestAttemptRepository contestAttemptRepository;
    private final AttemptMapper attemptMapper;

    @Override
    public AttemptResponse startContest(Long contestId , Long userId) {

        ContestDto contest;
        try {

            contest = contestServiceClient.getContestById(contestId);

        } catch (FeignException ex) {

            if (ex.status() == 404) {
                throw new ContestNotFoundException("Contest not found.");
            }

            throw ex;
        }

        Long participantId;
        try {
            participantId = contestServiceClient.getParticipantId(contestId, userId);
        } catch (FeignException e) {
            throw new IllegalStateException(e.getMessage());
        }

        if (contestAttemptRepository.existsByParticipantId(
                participantId)) {

            throw new IllegalStateException(
                    "Contest already started");
        }

        ContestAttempt attempt = ContestAttempt.builder()
                .contestId(contestId)
                .participantId(participantId)
                .status(AttemptStatus.IN_PROGRESS)
                .score(BigDecimal.ZERO)
                .totalQuestions(contest.getTotalQuestions())
                .correctAnswers(0)
                .wrongAnswers(0)
                .unansweredQuestions(contest.getTotalQuestions())
                .startedAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusSeconds(Long.parseLong(contest.getDurationInSeconds())))
                .build();

        contestAttemptRepository.save(attempt);

        contestServiceClient.setParticipantStatus(contestId , userId , ParticipantStatus.STARTED);

        return attemptMapper.toResponse(attempt);
    }

    @Override
    public AttemptResponse getAttempt(Long attemptId, Long userId) {
        return null;
    }

    @Override
    public AttemptResponse submitContest(Long contestId, Long userId) {
        Long participantId = contestServiceClient.getParticipantId(contestId, userId);

        ContestAttempt att empt =
                contestAttemptRepository.findById(participantId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Attempt not found"));




        if (attempt.getStatus()
                != AttemptStatus.IN_PROGRESS) {

            throw new IllegalStateException(
                    "Attempt already submitted");
        }

          //kafka evaluation service......
//        evaluationService.evaluateAttempt(attempt);

        attempt.setSubmittedAt(LocalDateTime.now());

        attempt.setStatus(AttemptStatus.SUBMITTED);

        attempt.setTimeTakenSeconds(
                (int) Duration.between(
                                attempt.getStartedAt(),
                                attempt.getSubmittedAt())
                        .getSeconds());

        //set the status of the participant table.....
        contestServiceClient.setParticipantStatus(contestId , userId , ParticipantStatus.SUBMITTED);

        contestAttemptRepository.save(attempt);

        return attemptMapper.toResponse(attempt);
    }

    @Override
    public ContestResultResponse getResult(Long attemptId, Long userId) {
        return null;
    }
}
