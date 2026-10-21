package io.traceiq.storage.entity;

import io.traceiq.core.model.AnalysisStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "traceiq_analysis_result")
public class AnalysisResultEntity {

    @Id
    @Column(nullable = false, updatable = false, length = 36)
    private String id;

    @Column(name = "incident_id", nullable = false, length = 36)
    private String incidentId;

    @Column(name = "root_cause", columnDefinition = "TEXT")
    private String rootCause;

    @Column(columnDefinition = "TEXT")
    private String explanation;

    @Column(name = "suggested_fix", columnDefinition = "TEXT")
    private String suggestedFix;

    @Column(nullable = false, length = 100)
    private String provider;

    @Column(name = "confidence_score")
    private Double confidenceScore;

    @Column(name = "analyzed_at", nullable = false)
    private Instant analyzedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private AnalysisStatus status;

    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

    protected AnalysisResultEntity() {
        // Required by JPA
    }

    public AnalysisResultEntity(
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

        this.id = id;
        this.incidentId = incidentId;
        this.rootCause = rootCause;
        this.explanation = explanation;
        this.suggestedFix = suggestedFix;
        this.provider = provider;
        this.confidenceScore = confidenceScore;
        this.analyzedAt = analyzedAt;
        this.status = status;
        this.errorMessage = errorMessage;
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