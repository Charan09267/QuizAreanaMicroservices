package net.ContestAttempMicroService.Client;

import net.ContestAttempMicroService.dto.ContestDto;
import net.ContestAttempMicroService.entity.ParticipantStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${services.contest.name}")
public interface ContestServiceClient {

    @GetMapping("/contests/{id}")
    ContestDto getContestById(@PathVariable("id") Long id);

    @GetMapping("/contests/{id}/participants/{userId}")
    Long getParticipantId(@PathVariable("id") Long id,
                          @PathVariable("userId") Long userId);

    @PostMapping("/contests/{contestId}/participants/{userId}")
    void setParticipantStatus(@PathVariable("contestId") Long contestId,
                              @PathVariable("userId") Long userId,
                              @RequestBody ParticipantStatus status);
}
