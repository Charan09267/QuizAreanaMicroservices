package net.ContestAttempMicroService.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContestDto {

    private Long id;
    private String title;
    private String description;
    private String status;
    private Integer totalQuestions;
    private String durationInSeconds;
    private String startTime;
    private String endTime;

}
