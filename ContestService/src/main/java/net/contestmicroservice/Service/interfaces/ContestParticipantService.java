package net.contestmicroservice.Service.interfaces;

import net.contestmicroservice.dto.response.ParticipantResponse;

import java.util.List;

public interface ContestParticipantService {
    ParticipantResponse joinContest(Long userId , Long contestId);

    List<ParticipantResponse> getParticipants(
            Long contestId
    );

    Long getParticipantId(Long contestId, Long userId);
}
