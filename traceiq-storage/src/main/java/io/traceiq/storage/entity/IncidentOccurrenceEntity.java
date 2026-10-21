package io.traceiq.storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "traceiq_incident_occurrence")
public class IncidentOccurrenceEntity {

    @Id
    @Column(nullable = false, updatable = false, length = 36)
    private String id;

    @Column(name = "incident_id", nullable = false, length = 36)
    private String incidentId;

    @Column(name = "occurred_at", nullable = false)
    private Instant occurredAt;

    @Column(name = "stack_trace", columnDefinition = "TEXT")
    private String stackTrace;

    protected IncidentOccurrenceEntity() {
        // Required by JPA
    }

    public IncidentOccurrenceEntity(
            String id,
            String incidentId,
            Instant occurredAt,
            String stackTrace) {

        this.id = id;
        this.incidentId = incidentId;
        this.occurredAt = occurredAt;
        this.stackTrace = stackTrace;
    }

    public String getId() {
        return id;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }

    public String getStackTrace() {
        return stackTrace;
    }
}