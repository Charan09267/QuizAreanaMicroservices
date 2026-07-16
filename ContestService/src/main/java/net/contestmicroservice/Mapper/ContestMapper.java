package net.contestmicroservice.Mapper;

import net.contestmicroservice.dto.response.ContestResponse;
import net.contestmicroservice.entity.Contest;
import org.springframework.stereotype.Component;

@Component
public class ContestMapper {

    public ContestResponse toResponse(Contest contest) {

        return ContestResponse.builder()
                .id(contest.getId())
                .title(contest.getTitle())
                .description(contest.getDescription())
                .startTime(contest.getStartTime())
                .status(contest.getStatus())
                .endTime(contest.getEndTime())
                .build();
    }
}
