package net.company.evaluationservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationResult {

    private Long contestId;

    private Long participantId;

    private Long userId;

    private BigDecimal score;

    private Integer totalQuestions;

    private Integer correctAnswers;

    private Integer wrongAnswers;

    private Integer unansweredQuestions;

    private LocalDateTime submittedAt;

}
