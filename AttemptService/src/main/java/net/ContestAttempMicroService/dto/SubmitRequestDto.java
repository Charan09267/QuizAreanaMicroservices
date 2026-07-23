package net.ContestAttempMicroService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubmitRequestDto {

    private Long contestId;
    private Long userId;
    private List<AnswerDto> answers;
}
