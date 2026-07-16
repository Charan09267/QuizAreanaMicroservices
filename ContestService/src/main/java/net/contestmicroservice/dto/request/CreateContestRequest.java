package net.contestmicroservice.dto.request;

import lombok.Getter;
import lombok.Setter;
import net.contestmicroservice.entity.enums.ContestType;
import net.contestmicroservice.entity.enums.Visibility;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateContestRequest {


    private String title;


    private String description;



    private Visibility visibility;


    private LocalDateTime startTime;


    private LocalDateTime endTime;


    private Integer durationSeconds;


    private Integer maxParticipants;


    private ContestType contestType;

}
