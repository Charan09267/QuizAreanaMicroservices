package net.contestmicroservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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


    private String status;


    private Integer totalQuestions;

}