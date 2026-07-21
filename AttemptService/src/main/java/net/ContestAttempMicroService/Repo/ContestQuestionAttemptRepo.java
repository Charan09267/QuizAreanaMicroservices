package net.ContestAttempMicroService.Repo;

import net.ContestAttempMicroService.entity.ContestQuestionAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestQuestionAttemptRepo extends JpaRepository<ContestQuestionAttempt, Long> {
}
