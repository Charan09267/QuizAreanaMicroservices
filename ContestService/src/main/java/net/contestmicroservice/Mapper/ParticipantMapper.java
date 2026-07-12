package net.contestmicroservice.Mapper;

import net.contestmicroservice.dto.response.ParticipantResponse;
import net.contestmicroservice.entity.ContestParticipant;

public class ParticipantMapper {
    public ParticipantResponse toResponse(
            ContestParticipant participant
    ) {

        return ParticipantResponse.builder()
                .id(participant.getId())
                .userId(participant.getUserId())
                .joinedAt(participant.getJoinedAt())
                .status(participant.getStatus())
                .build();
    }
}
