package net.ContestAttempMicroService.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import net.ContestAttempMicroService.Client.ContestServiceClient;
import net.ContestAttempMicroService.Repo.ContestAttemptRepository;
import net.ContestAttempMicroService.dto.AttemptResponse;
import net.ContestAttempMicroService.dto.ContestDto;
import net.ContestAttempMicroService.dto.ContestResultResponse;
import net.ContestAttempMicroService.entity.AttemptStatus;
import net.ContestAttempMicroService.entity.ContestAttempt;
import net.company.common.Exception.ContestNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContestAttemptServiceImpl implements ContestAttemptService {
    private final ContestServiceClient contestServiceClient;
    private final ContestAttemptRepository contestAttemptRepository;

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

        return null;
    }

    @Override
    public AttemptResponse getAttempt(Long attemptId, Long userId) {
        return null;
    }

    @Override
    public AttemptResponse submitContest(Long attemptId, Long userId) {
        return null;
    }

    @Override
    public ContestResultResponse getResult(Long attemptId, Long userId) {
        return null;
    }
}
