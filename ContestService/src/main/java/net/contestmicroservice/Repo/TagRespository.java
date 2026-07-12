package net.contestmicroservice.Repo;

import net.contestmicroservice.dto.response.ContestResponse;
import net.contestmicroservice.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRespository extends JpaRepository<Tag, Long> {
}
