package net.contestmicroservice.Repo;

import net.contestmicroservice.entity.ContestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepo extends JpaRepository<ContestQuestion, Long> {

    long countByContestId(Long contestId);

    List<ContestQuestion> findByContestIdOrderByOrderIndex(Long contestId);

}
