package net.ContestAttempMicroService.Mapper;

import net.ContestAttempMicroService.dto.AttemptResponse;
import net.ContestAttempMicroService.entity.ContestAttempt;
import org.springframework.stereotype.Component;

@Component
public class AttemptMapper {

    public AttemptResponse toResponse(
            ContestAttempt attempt
    ) {

        return AttemptResponse.builder()
                .id(attempt.getId())
                .contestId(attempt.getContestId())
                .participantId(attempt.getParticipantId())
                .startedAt(attempt.getStartedAt())
                .submittedAt(attempt.getSubmittedAt())
                .status(attempt.getStatus())
                .score(attempt.getScore())
                .totalQuestions(attempt.getTotalQuestions())
                .correctAnswers(attempt.getCorrectAnswers())
                .wrongAnswers(attempt.getWrongAnswers())
                .unansweredQuestions(attempt.getUnansweredQuestions())
                .timeTakenSeconds(attempt.getTimeTakenSeconds())
                .build();
    }
}
