package io.traceiq.core.model;

import java.util.List;
import java.util.Objects;

public class AnalysisRequest {

    private final String incidentId;
    private final String exceptionType;
    private final String message;
    private final List<Evidence> evidence;

    public AnalysisRequest(
            String incidentId,
            String exceptionType,
            String message,
            List<Evidence> evidence) {

        this.incidentId = Objects.requireNonNull(
                incidentId, "incidentId must not be null");

        this.exceptionType = Objects.requireNonNull(
                exceptionType, "exceptionType must not be null");

        this.message = message;

        this.evidence = List.copyOf(
                Objects.requireNonNull(
                        evidence, "evidence must not be null"));
    }

    public String getIncidentId() {
        return incidentId;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public String getMessage() {
        return message;
    }

    public List<Evidence> getEvidence() {
        return evidence;
    }
}