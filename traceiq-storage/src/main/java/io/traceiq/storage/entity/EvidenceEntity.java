package io.traceiq.storage.entity;

import io.traceiq.core.model.EvidenceType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "traceiq_evidence")
public class EvidenceEntity {

    @Id
    @Column(nullable = false, updatable = false, length = 36)
    private String id;

    @Column(name = "incident_id", nullable = false, length = 36)
    private String incidentId;

    @Column(name = "occurrence_id", length = 36)
    private String occurrenceId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EvidenceType type;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "collected_at", nullable = false)
    private Instant collectedAt;

    protected EvidenceEntity() {
        // Required by JPA
    }

    public EvidenceEntity(
            String id,
            String incidentId,
            String occurrenceId,
            EvidenceType type,
            String content,
            Instant collectedAt) {

        this.id = id;
        this.incidentId = incidentId;
        this.occurrenceId = occurrenceId;
        this.type = type;
        this.content = content;
        this.collectedAt = collectedAt;
    }

    public String getId() {
        return id;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public String getOccurrenceId() {
        return occurrenceId;
    }

    public EvidenceType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public Instant getCollectedAt() {
        return collectedAt;
    }
}