package net.ContestAttempMicroService.Client;

import net.ContestAttempMicroService.dto.ContestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "contest-service",
        url = "${services.contest.url}"
)
public interface ContestServiceClient {
    @GetMapping("/contests/{id}")
    ContestDto getContestById(@PathVariable Long id);

    @GetMapping("/contests/{id}/participants/{userId}")
    Long getParticipantId(@PathVariable Long id , @PathVariable Long userId);
}
