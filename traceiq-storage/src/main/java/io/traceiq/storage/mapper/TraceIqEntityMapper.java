package io.traceiq.storage.mapper;

import io.traceiq.core.model.AnalysisResult;
import io.traceiq.core.model.Evidence;
import io.traceiq.core.model.Incident;
import io.traceiq.core.model.IncidentOccurrence;
import io.traceiq.storage.entity.AnalysisResultEntity;
import io.traceiq.storage.entity.EvidenceEntity;
import io.traceiq.storage.entity.IncidentEntity;
import io.traceiq.storage.entity.IncidentOccurrenceEntity;

public final class TraceIqEntityMapper {

    private TraceIqEntityMapper() {
        // Utility class
    }

    public static IncidentEntity toEntity(Incident incident) {
        return new IncidentEntity(
                incident.getId(),
                incident.getFingerprint(),
                incident.getExceptionType(),
                incident.getMessage(),
                incident.getFirstSeenAt(),
                incident.getLastSeenAt(),
                incident.getOccurrenceCount(),
                incident.getStatus()
        );
    }

    public static Incident toModel(IncidentEntity entity) {
        return Incident.restore(
                entity.getId(),
                entity.getFingerprint(),
                entity.getExceptionType(),
                entity.getMessage(),
                entity.getFirstSeenAt(),
                entity.getLastSeenAt(),
                entity.getOccurrenceCount(),
                entity.getStatus()
        );
    }

    public static IncidentOccurrenceEntity toEntity(
            IncidentOccurrence occurrence) {

        return new IncidentOccurrenceEntity(
                occurrence.getId(),
                occurrence.getIncidentId(),
                occurrence.getOccurredAt(),
                occurrence.getStackTrace()
        );
    }

    public static IncidentOccurrence toModel(
            IncidentOccurrenceEntity entity) {

        return IncidentOccurrence.restore(
                entity.getId(),
                entity.getIncidentId(),
                entity.getOccurredAt(),
                entity.getStackTrace()
        );
    }

    public static EvidenceEntity toEntity(Evidence evidence) {
        return new EvidenceEntity(
                evidence.getId(),
                evidence.getIncidentId(),
                evidence.getOccurrenceId(),
                evidence.getType(),
                evidence.getContent(),
                evidence.getCollectedAt()
        );
    }

    public static Evidence toModel(EvidenceEntity entity) {
        return Evidence.restore(
                entity.getId(),
                entity.getIncidentId(),
                entity.getOccurrenceId(),
                entity.getType(),
                entity.getContent(),
                entity.getCollectedAt()
        );
    }

    public static AnalysisResultEntity toEntity(
            AnalysisResult result) {

        return new AnalysisResultEntity(
                result.getId(),
                result.getIncidentId(),
                result.getRootCause(),
                result.getExplanation(),
                result.getSuggestedFix(),
                result.getProvider(),
                result.getConfidenceScore(),
                result.getAnalyzedAt(),
                result.getStatus(),
                result.getErrorMessage()
        );
    }

    public static AnalysisResult toModel(
            AnalysisResultEntity entity) {

        return AnalysisResult.restore(
                entity.getId(),
                entity.getIncidentId(),
                entity.getRootCause(),
                entity.getExplanation(),
                entity.getSuggestedFix(),
                entity.getProvider(),
                entity.getConfidenceScore(),
                entity.getAnalyzedAt(),
                entity.getStatus(),
                entity.getErrorMessage()
        );
    }
}