package net.ContestAttempMicroService.service;

import net.ContestAttempMicroService.dto.AttemptResponse;
import net.ContestAttempMicroService.dto.ContestResultResponse;
import net.ContestAttempMicroService.dto.SubmitRequestDto;

public interface ContestAttemptService {
    AttemptResponse startContest(Long contestId , Long userId);

    AttemptResponse getAttempt(Long attemptId , Long userId);

    String submitContest(Long attemptId , Long userId , SubmitRequestDto submitRequestDto);

    ContestResultResponse getResult(Long attemptId , Long userId);
}
