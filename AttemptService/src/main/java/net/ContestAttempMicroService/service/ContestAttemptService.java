package net.ContestAttempMicroService.service;

import net.ContestAttempMicroService.dto.AttemptResponse;
import net.ContestAttempMicroService.dto.ContestResultResponse;

public interface ContestAttemptService {
    AttemptResponse startContest(Long contestId , Long userId);

    AttemptResponse getAttempt(Long attemptId , Long userId);

    AttemptResponse submitContest(Long attemptId , Long userId);

    ContestResultResponse getResult(Long attemptId , Long userId);
}
