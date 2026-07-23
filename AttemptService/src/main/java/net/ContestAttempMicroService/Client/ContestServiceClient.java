package net.ContestAttempMicroService.Client;

import net.ContestAttempMicroService.dto.ContestDto;
import net.ContestAttempMicroService.entity.ParticipantStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "contest-service"
)
public interface ContestServiceClient {
    @GetMapping("/contests/{id}")
    ContestDto getContestById(@PathVariable Long id);

    @GetMapping("/contests/{id}/participants/{userId}")
    Long getParticipantId(@PathVariable Long id , @PathVariable Long userId);

    @PostMapping("/contest/{contestId}/participants/{userId}")
    void setParticipantStatus(@PathVariable Long contestId, @PathVariable Long userId, @RequestBody ParticipantStatus status);


}
