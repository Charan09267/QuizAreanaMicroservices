package net.company.evaluationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssessmentSubmittedEvent {

    private Long contestId;

    private Long participantId;

    private Long userId;

    private List<AnswerDto> answers;

    private LocalDateTime submittedAt;
}