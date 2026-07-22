package net.ContestAttempMicroService.Repo;

import net.ContestAttempMicroService.entity.ContestAttempt;
import net.ContestAttempMicroService.entity.ContestQuestionAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestAttemptRepository extends JpaRepository<ContestAttempt, Long> {
    boolean existsByParticipantId(
            Long participantId
    );
}
