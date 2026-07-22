package net.contestmicroservice.Scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.contestmicroservice.Service.interfaces.ContestService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ContestScheduler {

    private final ContestService contestService;

    @Scheduled(fixedRate = 60000)
    public void activateContests() {

        log.info("Checking contests to activate...");

        contestService.activateContests();


    }

    @Scheduled(fixedRate = 60000)
    public void completeContests() {

        log.info("Checking contests to complete...");

        contestService.completeContests();

    }
}

