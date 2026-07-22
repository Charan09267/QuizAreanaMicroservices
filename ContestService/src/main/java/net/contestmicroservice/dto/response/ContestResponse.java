package net.contestmicroservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.contestmicroservice.entity.enums.ContestStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ContestResponse {


    private Long id;


    private String title;


    private String description;


    private LocalDateTime startTime;


    private LocalDateTime endTime;


    private ContestStatus status;


    private Integer totalQuestions;

    private Integer durationInSeconds;

}