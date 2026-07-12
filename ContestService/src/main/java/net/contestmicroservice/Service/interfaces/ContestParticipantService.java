package net.contestmicroservice.Service.interfaces;

import net.contestmicroservice.dto.response.ParticipantResponse;

import java.util.List;

public interface ContestParticipantService {
    ParticipantResponse joinContest(Long contestId);

    List<ParticipantResponse> getParticipants(
            Long contestId
    );
}
