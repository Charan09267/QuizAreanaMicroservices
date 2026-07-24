package net.ContestAttempMicroService.Repo;

import net.ContestAttempMicroService.entity.ContestAttempt;
import net.ContestAttempMicroService.entity.ContestQuestionAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContestAttemptRepository extends JpaRepository<ContestAttempt, Long> {
    boolean existsByParticipantId(
            Long participantId
    );

    Optional<ContestAttempt> findByParticipantId(Long participantId);
}
