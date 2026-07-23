package net.ContestAttempMicroService.controller;


import lombok.RequiredArgsConstructor;
import net.ContestAttempMicroService.dto.AnswerDto;
import net.ContestAttempMicroService.dto.AttemptResponse;
import net.ContestAttempMicroService.dto.ContestResultResponse;
import net.ContestAttempMicroService.dto.SubmitRequestDto;
import net.ContestAttempMicroService.service.ContestAttemptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attempts")
@RequiredArgsConstructor
public class ContestAttemptController {

    private final ContestAttemptService contestAttemptService;

    @PostMapping("/{contestId}/start")
    public ResponseEntity<AttemptResponse> startContest(
            @PathVariable Long contestId,
            @RequestHeader("X-USER-ID") Long userId) {

        return ResponseEntity.ok(
                contestAttemptService.startContest(contestId , userId));
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitContest(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestBody SubmitRequestDto request) {

        return ResponseEntity.ok(
                contestAttemptService.submitContest(request.getContestId(), userId, request)
        );

    }

    @GetMapping("/{attemptId}")
    public ResponseEntity<AttemptResponse> getAttempt(
            @PathVariable Long attemptId , @RequestHeader("X-USER-ID") Long userId) {

        return ResponseEntity.ok(
                contestAttemptService.getAttempt(attemptId , userId));
    }
//
//    @GetMapping("/{attemptId}/result")
//    public ResponseEntity<ContestResultResponse> getResult(
//            @PathVariable Long attemptId, @RequestHeader("X-USER-ID") Long userId) {
//
//        return ResponseEntity.ok(
//                contestAttemptService.getResult(attemptId , userId));
//    }
}
