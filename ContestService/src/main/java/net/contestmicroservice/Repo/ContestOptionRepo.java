package net.contestmicroservice.Repo;

import net.contestmicroservice.entity.ContestOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestOptionRepo extends JpaRepository<ContestOption, Long> {
}
