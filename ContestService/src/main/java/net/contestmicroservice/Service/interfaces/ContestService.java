package net.contestmicroservice.Service.interfaces;

import net.contestmicroservice.dto.request.CreateContestRequest;
import net.contestmicroservice.dto.request.UpdateContestRequest;
import net.contestmicroservice.dto.response.ContestResponse;

import java.util.List;

public interface ContestService {
    ContestResponse createContest(CreateContestRequest request);

    ContestResponse getContestById(Long contestId);

    List<ContestResponse> getAllContests();

    ContestResponse updateContest(
            Long contestId,
            UpdateContestRequest request);

    void deleteContest(Long contestId);

    ContestResponse publishContest(Long contestId);

    ContestResponse cancelContest(Long contestId);

    void activateContests();

    void completeContests();
}
