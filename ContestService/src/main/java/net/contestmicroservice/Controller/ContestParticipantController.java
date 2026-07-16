package net.contestmicroservice.Controller;

import lombok.RequiredArgsConstructor;
import net.contestmicroservice.Service.interfaces.ContestParticipantService;
import net.contestmicroservice.dto.response.ParticipantResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contests")
@RequiredArgsConstructor
public class ContestParticipantController {

    private final ContestParticipantService participantService;

    @PostMapping("/{contestId}/join")
    public ResponseEntity<ParticipantResponse> joinContest(
            @RequestHeader("X-USER-ID") Long userId,
            @PathVariable Long contestId
    ) {

        return ResponseEntity.ok(
                participantService.joinContest(userId , contestId)
        );
    }

    @GetMapping("/{contestId}/participants")
    public ResponseEntity<List<ParticipantResponse>> getParticipants(
            @PathVariable Long contestId
    ) {

        return ResponseEntity.ok(
                participantService.getParticipants(contestId)
        );
    }

}

