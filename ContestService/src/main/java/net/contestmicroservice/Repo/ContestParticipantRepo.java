package net.contestmicroservice.Repo;

import net.contestmicroservice.entity.ContestParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContestParticipantRepo extends JpaRepository<ContestParticipant, Long> {

    boolean existsByContestIdAndUserId(
            Long contestId,
            Long userId
    );

    Optional<ContestParticipant> findByContestIdAndUserId(
            Long contestId,
            Long userId
    );

    List<ContestParticipant> findByContestId(
            Long contestId
    );

    long countByContestId(
            Long contestId
    );
}
