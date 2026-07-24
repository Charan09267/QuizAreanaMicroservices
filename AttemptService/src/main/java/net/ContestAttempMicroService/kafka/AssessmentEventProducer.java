package net.ContestAttempMicroService.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ContestAttempMicroService.dto.AssessmentSubmittedEvent;
import net.ContestAttempMicroService.dto.SubmitRequestDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class AssessmentEventProducer {

    private static final String TOPIC = "assessment-submitted";

    private final KafkaTemplate<String, AssessmentSubmittedEvent> kafkaTemplate;

    public CompletableFuture<SendResult<String, AssessmentSubmittedEvent>> publish(AssessmentSubmittedEvent event) {

        return kafkaTemplate.send(
                TOPIC,
                event.getUserId().toString(),   // Kafka Key
                event                           // Kafka Value
        ).whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Published successfully");
            } else {
                log.error("Failed", ex);
            }
        });
    }
}
