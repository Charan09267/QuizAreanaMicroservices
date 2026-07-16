package net.contestmicroservice.Service.interfaces;

import net.contestmicroservice.dto.request.CreateContestRequest;
import net.contestmicroservice.dto.request.UpdateContestRequest;
import net.contestmicroservice.dto.response.ContestResponse;

import java.util.List;

public interface ContestService {
    ContestResponse createContest(Long userId , CreateContestRequest request);

    ContestResponse getContestById(Long contestId);

    List<ContestResponse> getAllContests();

    ContestResponse updateContest(
            Long contestId,
            UpdateContestRequest request,
            Long userId);

    void deleteContest(Long contestId , Long userId);

    ContestResponse publishContest(Long contestId , Long userId);

    ContestResponse cancelContest(Long contestId , Long userId);

    void activateContests();

    void completeContests();
}
