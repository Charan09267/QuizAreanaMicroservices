package net.contestmicroservice.Controller;

import lombok.RequiredArgsConstructor;
import net.company.common.Exception.ResourceNotFoundException;
import net.contestmicroservice.Service.implementations.ContestServiceImpl;
import net.contestmicroservice.Service.interfaces.ContestService;
import net.contestmicroservice.dto.request.CreateContestRequest;
import net.contestmicroservice.dto.request.UpdateContestRequest;
import net.contestmicroservice.dto.response.ContestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contests")
@RequiredArgsConstructor
public class ContestController {
    private final ContestService contestService;

    //creating a contest
    @PostMapping
    public ResponseEntity<ContestResponse> createContest(
            @RequestHeader("X-USER-ID")  Long userId,
             @RequestBody CreateContestRequest request) {

        return ResponseEntity.ok(
                contestService.createContest( userId , request)
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
             @RequestBody UpdateContestRequest request,
            @RequestHeader("X-USER-ID") Long userId) {

        return ResponseEntity.ok(
                contestService.updateContest(id, request , userId)
        );
    }

    //Delete a contest......
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContest(
            @PathVariable Long id,
            @RequestHeader("X-USER-ID") Long userId) {

        contestService.deleteContest(id , userId);

        return ResponseEntity.noContent().build();
    }

    //publish a contest to live......
    @PostMapping("/{contestId}/publish")
    public ResponseEntity<ContestResponse> publishContest(
            @PathVariable Long contestId,
            @RequestHeader("X-USER-ID")Long userId) {

        return ResponseEntity.ok(
                contestService.publishContest(contestId , userId)
        );
    }

    //Cancel a contest.....
    @PostMapping("/{contestId}/cancel")
    public ResponseEntity<ContestResponse> cancelContest(
            @PathVariable Long contestId ,
            @RequestHeader("X-USER-ID")Long userId) {

        return ResponseEntity.ok(
                contestService.cancelContest(contestId ,userId )
        );
    }

}
