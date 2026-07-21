package net.ContestAttempMicroService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "contest_attempts",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_attempt_participant",
                        columnNames = "participant_id"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContestAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contest_id", nullable = false)
    private Long contestId;

    @Column(name = "participant_id", nullable = false)
    private Long participantId;

    @Column(nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal score = BigDecimal.ZERO;

    @Column(name = "total_questions")
    @Builder.Default
    private Integer totalQuestions = 0;

    @Column(name = "correct_answers")
    @Builder.Default
    private Integer correctAnswers = 0;

    @Column(name = "wrong_answers")
    @Builder.Default
    private Integer wrongAnswers = 0;

    @Column(name = "unanswered_questions")
    @Builder.Default
    private Integer unansweredQuestions = 0;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private AttemptStatus status;

    @Column(name = "time_taken_seconds")
    private Integer timeTakenSeconds;

    @OneToMany(
            mappedBy = "contestAttempt",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<ContestQuestionAttempt> questionAttempts = new ArrayList<>();
}