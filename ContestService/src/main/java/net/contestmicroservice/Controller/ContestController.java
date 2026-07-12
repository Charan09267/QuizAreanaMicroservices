package net.contestmicroservice.Controller;

import lombok.RequiredArgsConstructor;
import net.company.common.Exception.ResourceNotFoundException;
import net.contestmicroservice.Service.implementations.ContestServiceImpl;
import net.contestmicroservice.dto.request.CreateContestRequest;
import net.contestmicroservice.dto.request.UpdateContestRequest;
import net.contestmicroservice.dto.response.ContestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contests")
@RequiredArgsConstructor
public class ContestController {
    private final ContestServiceImpl contestService;

    //creating a contest
    @PostMapping
    public ResponseEntity<ContestResponse> createContest(
             @RequestBody CreateContestRequest request) {

        return ResponseEntity.ok(
                contestService.createContest(request)
        );
    }

    //Get the contest by id....
    @GetMapping("/{id}")
    public ResponseEntity<ContestResponse> getContest(
            @PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    contestService.getContestById(id)
            );
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }

    //get all the available contests...
    @GetMapping
    public ResponseEntity<List<ContestResponse>> getAllContests() {

        return ResponseEntity.ok(
                contestService.getAllContests()
        );
    }


    //Update a contest.......
    @PutMapping("/{id}")
    public ResponseEntity<ContestResponse> updateContest(
            @PathVariable Long id,
             @RequestBody UpdateContestRequest request) {

        return ResponseEntity.ok(
                contestService.updateContest(id, request)
        );
    }

    //Delete a contest......
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContest(
            @PathVariable Long id) {

        contestService.deleteContest(id);

        return ResponseEntity.noContent().build();
    }

    //publish a contest to live......
    @PostMapping("/{contestId}/publish")
    public ResponseEntity<ContestResponse> publishContest(
            @PathVariable Long contestId) {

        return ResponseEntity.ok(
                contestService.publishContest(contestId)
        );
    }

    //Cancel a contest.....
    @PostMapping("/{contestId}/cancel")
    public ResponseEntity<ContestResponse> cancelContest(
            @PathVariable Long contestId) {

        return ResponseEntity.ok(
                contestService.cancelContest(contestId)
        );
    }

//    @GetMapping("/{contestId}/leaderboard")
//    public ResponseEntity<List<LeaderboardResponse>>
//    getLeaderboard(
//            @PathVariable Long contestId){
//
//        return ResponseEntity.ok(
//                contestService.getLeaderboard(
//                        contestId));
//    }
}
