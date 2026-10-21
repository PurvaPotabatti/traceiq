package io.traceiq.core.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class IncidentOccurrence {

    private final String id;
    private final String incidentId;
    private final Instant occurredAt;
    private final String stackTrace;

    private IncidentOccurrence(
            String id,
            String incidentId,
            Instant occurredAt,
            String stackTrace) {

        this.id = Objects.requireNonNull(id, "id must not be null");
        this.incidentId = Objects.requireNonNull(
                incidentId, "incidentId must not be null");
        this.occurredAt = Objects.requireNonNull(
                occurredAt, "occurredAt must not be null");
        this.stackTrace = stackTrace;
    }

    public IncidentOccurrence(
            String incidentId,
            Instant occurredAt,
            String stackTrace) {

        this.id = UUID.randomUUID().toString();

        this.incidentId = Objects.requireNonNull(
                incidentId, "incidentId must not be null");

        this.occurredAt = Objects.requireNonNull(
                occurredAt, "occurredAt must not be null");

        this.stackTrace = stackTrace;
    }

    public static IncidentOccurrence restore(
            String id,
            String incidentId,
            Instant occurredAt,
            String stackTrace) {

        return new IncidentOccurrence(
                id,
                incidentId,
                occurredAt,
                stackTrace);
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