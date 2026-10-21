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
    private final AnalysisStatus status;
    private final String errorMessage;

    private AnalysisResult(
            String id,
            String incidentId,
            String rootCause,
            String explanation,
            String suggestedFix,
            String provider,
            Double confidenceScore,
            Instant analyzedAt,
            AnalysisStatus status,
            String errorMessage) {

        this.id = Objects.requireNonNull(id, "id must not be null");
        this.incidentId = Objects.requireNonNull(
                incidentId, "incidentId must not be null");
        this.rootCause = rootCause;
        this.explanation = explanation;
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
        this.status = Objects.requireNonNull(
                status, "status must not be null");
        this.errorMessage = errorMessage;
    }

    public AnalysisResult(
            String incidentId,
            String rootCause,
            String explanation,
            String suggestedFix,
            String provider,
            Double confidenceScore,
            Instant analyzedAt,
            AnalysisStatus status,
            String errorMessage) {

        this.id = UUID.randomUUID().toString();

        this.incidentId = Objects.requireNonNull(
                incidentId, "incidentId must not be null");

        this.rootCause = rootCause;

        this.explanation = explanation;

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

        this.status = Objects.requireNonNull(
                status, "status must not be null");

        this.errorMessage = errorMessage;
    }

    public static AnalysisResult restore(
            String id,
            String incidentId,
            String rootCause,
            String explanation,
            String suggestedFix,
            String provider,
            Double confidenceScore,
            Instant analyzedAt,
            AnalysisStatus status,
            String errorMessage) {

        return new AnalysisResult(
                id,
                incidentId,
                rootCause,
                explanation,
                suggestedFix,
                provider,
                confidenceScore,
                analyzedAt,
                status,
                errorMessage);
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

    public AnalysisStatus getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}