package net.company.evaluationservice.service;

import net.company.evaluationservice.dto.AssessmentSubmittedEvent;

public interface EvaluationService {

    void evaluate(AssessmentSubmittedEvent event);

}
