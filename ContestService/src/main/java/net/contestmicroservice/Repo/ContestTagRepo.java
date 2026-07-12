package net.contestmicroservice.Repo;

import net.contestmicroservice.entity.ContestTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestTagRepo extends JpaRepository<ContestTag, Long> {
}
