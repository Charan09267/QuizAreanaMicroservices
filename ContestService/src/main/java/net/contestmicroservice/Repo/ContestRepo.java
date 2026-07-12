package net.contestmicroservice.Repo;

import net.contestmicroservice.entity.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ContestRepo extends JpaRepository<Contest,Long> {

    @Query("""
           SELECT c
           FROM Contest c
           WHERE c.status = 'UPCOMING'
           AND c.startTime <= :currentTime
    """)
    List<Contest> findContestsToActivate(LocalDateTime currentTime);

    @Query("""
           SELECT c
           FROM Contest c
           WHERE c.status = 'LIVE'
           AND c.endTime <= :currentTime
    """)
    List<Contest> findContestsToComplete(LocalDateTime currentTime);
}
