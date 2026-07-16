package net.contestmicroservice.Service.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.company.common.Exception.ResourceNotFoundException;
import net.contestmicroservice.Mapper.ContestMapper;
import net.contestmicroservice.Repo.ContestRepo;
import net.contestmicroservice.Repo.QuestionRepo;
import net.contestmicroservice.Service.interfaces.ContestService;
import net.contestmicroservice.dto.request.CreateContestRequest;
import net.contestmicroservice.dto.request.UpdateContestRequest;
import net.contestmicroservice.dto.response.ContestResponse;
import net.contestmicroservice.entity.Contest;
import net.contestmicroservice.entity.enums.ContestStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContestServiceImpl implements ContestService {

    private final ContestRepo contestRepository;
    private final ContestMapper contestMapper;
    private final QuestionRepo questionRepository;

    @Override
    public ContestResponse createContest(Long userId , CreateContestRequest request) {


        Contest contest = Contest.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .createdBy(userId)
                .visibility(request.getVisibility())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .durationSeconds(request.getDurationSeconds())
                .maxParticipants(request.getMaxParticipants())
                .contestType(request.getContestType())
                .status(ContestStatus.DRAFT)
                .build();

        Contest savedContest = contestRepository.save(contest);

        return contestMapper.toResponse(savedContest);
    }


    @Override
    public ContestResponse getContestById(Long contestId) {

        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contest not found"));

        return contestMapper.toResponse(contest);
    }

    @Override
    public List<ContestResponse> getAllContests() {

        return contestRepository.findAll()
                .stream()
                .map(contestMapper::toResponse)
                .toList();
    }

    @Override
    public ContestResponse updateContest(Long contestId,
                                         UpdateContestRequest request ,Long userId) {

        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contest not found"));

        if (contest.getStatus() == ContestStatus.LIVE ||
                contest.getStatus() == ContestStatus.COMPLETED ||
                contest.getStatus() == ContestStatus.CANCELLED || contest.getCreatedBy() != userId) {

            throw new IllegalStateException(
                    "Contest cannot be updated");
        }

        contest.setTitle(request.getTitle());
        contest.setDescription(request.getDescription());
        contest.setVisibility(request.getVisibility());
        contest.setStartTime(request.getStartTime());
        contest.setEndTime(request.getEndTime());
        contest.setDurationSeconds(request.getDurationSeconds());
        contest.setMaxParticipants(request.getMaxParticipants());
        contest.setContestType(request.getContestType());

        Contest updatedContest = contestRepository.save(contest);

        return contestMapper.toResponse(updatedContest);
    }


    @Override
    public void deleteContest(Long contestId , Long userId) {

        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contest not found"));

        if(!userId.equals(contest.getCreatedBy())){
            throw new IllegalStateException("User is not allowed to delete contest");
        }

        if( contest.getStatus() == ContestStatus.LIVE){
            throw new IllegalStateException(
                    "Cannot delete a contest which is live..."
            );
        }

        contestRepository.delete(contest);
    }



    @Override
    public ContestResponse publishContest(Long contestId , Long userId) {
        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() -> new ResourceNotFoundException("Contest not found!"));

        if ( !userId.equals(contest.getCreatedBy())) {
            throw new IllegalStateException("User is not allowed to publish contest");
        }

            if (contest.getStatus() != ContestStatus.DRAFT) {
                throw new IllegalStateException("Contest status is not DRAFT , so it may already been published....");
            }

            if (contest.getStartTime().isAfter(contest.getEndTime())) {
                throw new IllegalStateException(
                        "Start time must be before end time");
            }

            long questionCount =
                    questionRepository.countByContestId(contestId);

            if (questionCount == 0) {
                throw new IllegalStateException(
                        "Contest must contain at least one question"
                );
            }

            contest.setStatus(ContestStatus.UPCOMING);
            contestRepository.save(contest);

            return contestMapper.toResponse(contest);
        }

    @Override
    public ContestResponse cancelContest(Long contestId , Long userId) {

        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contest not found"));

        if (userId.equals(contest.getCreatedBy())) {
            throw new IllegalStateException("User is not allowed to cancel contest");
        }

        if (contest.getStatus() == ContestStatus.LIVE ||
                contest.getStatus() == ContestStatus.COMPLETED) {

            throw new IllegalStateException(
                    "Live or completed contests cannot be cancelled");
        }

        contest.setStatus(ContestStatus.CANCELLED);

        contestRepository.save(contest);

        return contestMapper.toResponse(contest);
    }


    @Override
    @Transactional
    public void activateContests() {
        List<Contest> contests =
                contestRepository.findContestsToActivate(
                        LocalDateTime.now());

        contests.forEach(contest -> {
            contest.setStatus(ContestStatus.LIVE);
            log.info("Contest {} activated", contest.getId());
        });
    }

    @Override
    @Transactional
    public void completeContests() {

        List<Contest> contests =
                contestRepository.findContestsToComplete(
                        LocalDateTime.now());

        contests.forEach(contest -> {
            contest.setStatus(ContestStatus.COMPLETED);
            log.info("Contest {} completed", contest.getId());
        });
    }
}
