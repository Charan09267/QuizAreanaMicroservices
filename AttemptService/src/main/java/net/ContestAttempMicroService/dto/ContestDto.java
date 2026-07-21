package net.ContestAttempMicroService.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContestDto {

    private String title;
    private String status;
    private Integer totalQuestions;
    private String totalDurationInSeconds;
    private String startTime;
    private String endTime;

}
