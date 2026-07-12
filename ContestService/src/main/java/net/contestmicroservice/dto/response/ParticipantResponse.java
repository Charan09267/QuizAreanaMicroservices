package net.contestmicroservice.dto.response;

import lombok.*;
import net.contestmicroservice.entity.enums.ParticipantStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantResponse {

    private Long id;

    private Long userId;

    private String username;

    private LocalDateTime joinedAt;

    private ParticipantStatus status;

}