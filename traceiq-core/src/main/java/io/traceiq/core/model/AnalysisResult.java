package io.traceiq.core.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class AnalysisResult {

    private final String id;
    private final String incidentId;
    private final String rootCause;
    private final String explanation;
    private final String suggestedFix;
    private final String provider;
    private final Double confidenceScore;
    private final Instant analyzedAt;

    public AnalysisResult(
            String incidentId,
            String rootCause,
            String explanation,
            String suggestedFix,
            String provider,
            Double confidenceScore,
            Instant analyzedAt) {

        this.id = UUID.randomUUID().toString();
        this.incidentId = Objects.requireNonNull(
                incidentId, "incidentId must not be null");
        this.rootCause = Objects.requireNonNull(
                rootCause, "rootCause must not be null");
        this.explanation = Objects.requireNonNull(
                explanation, "explanation must not be null");
        this.suggestedFix = suggestedFix;
        this.provider = Objects.requireNonNull(
                provider, "provider must not be null");

        if (confidenceScore != null
                && (confidenceScore < 0.0 || confidenceScore > 1.0)) {
            throw new IllegalArgumentException(
                    "confidenceScore must be between 0.0 and 1.0");
        }

        this.confidenceScore = confidenceScore;
        this.analyzedAt = Objects.requireNonNull(
                analyzedAt, "analyzedAt must not be null");
    }

    public String getId() {
        return id;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public String getRootCause() {
        return rootCause;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getSuggestedFix() {
        return suggestedFix;
    }

    public String getProvider() {
        return provider;
    }

    public Double getConfidenceScore() {
        return confidenceScore;
    }

    public Instant getAnalyzedAt() {
        return analyzedAt;
    }
}
