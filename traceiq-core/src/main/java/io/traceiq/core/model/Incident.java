package io.traceiq.core.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Incident {

    private final String id;
    private final String fingerprint;
    private final String exceptionType;
    private final String message;
    private final Instant firstSeenAt;

    private Instant lastSeenAt;
    private long occurrenceCount;
    private IncidentStatus status;

    private Incident(
            String id,
            String fingerprint,
            String exceptionType,
            String message,
            Instant firstSeenAt,
            Instant lastSeenAt,
            long occurrenceCount,
            IncidentStatus status) {

        this.id = Objects.requireNonNull(id, "id must not be null");
        this.fingerprint = Objects.requireNonNull(
                fingerprint, "fingerprint must not be null");
        this.exceptionType = Objects.requireNonNull(
                exceptionType, "exceptionType must not be null");
        this.message = message;
        this.firstSeenAt = Objects.requireNonNull(
                firstSeenAt, "firstSeenAt must not be null");
        this.lastSeenAt = Objects.requireNonNull(
                lastSeenAt, "lastSeenAt must not be null");
        this.occurrenceCount = occurrenceCount;

        if (occurrenceCount < 1) {
            throw new IllegalArgumentException(
                    "occurrenceCount must be at least 1");
        }

        this.status = Objects.requireNonNull(
                status, "status must not be null");
    }

    public Incident(
            String fingerprint,
            String exceptionType,
            String message,
            Instant firstSeenAt) {

        this.id = UUID.randomUUID().toString();

        this.fingerprint = Objects.requireNonNull(
                fingerprint, "fingerprint must not be null");

        this.exceptionType = Objects.requireNonNull(
                exceptionType, "exceptionType must not be null");

        this.message = message;

        this.firstSeenAt = Objects.requireNonNull(
                firstSeenAt, "firstSeenAt must not be null");

        this.lastSeenAt = firstSeenAt;
        this.occurrenceCount = 1;
        this.status = IncidentStatus.OPEN;
    }

    public static Incident restore(
            String id,
            String fingerprint,
            String exceptionType,
            String message,
            Instant firstSeenAt,
            Instant lastSeenAt,
            long occurrenceCount,
            IncidentStatus status) {

        return new Incident(
                id,
                fingerprint,
                exceptionType,
                message,
                firstSeenAt,
                lastSeenAt,
                occurrenceCount,
                status);
    }

    public String getId() {
        return id;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public String getMessage() {
        return message;
    }

    public Instant getFirstSeenAt() {
        return firstSeenAt;
    }

    public Instant getLastSeenAt() {
        return lastSeenAt;
    }

    public long getOccurrenceCount() {
        return occurrenceCount;
    }

    public IncidentStatus getStatus() {
        return status;
    }

    public void recordOccurrence(Instant occurredAt) {
        Objects.requireNonNull(
                occurredAt, "occurredAt must not be null");

        this.lastSeenAt = occurredAt.isAfter(this.lastSeenAt)
                ? occurredAt
                : this.lastSeenAt;

        this.occurrenceCount++;
    }

    public void resolve() {
        this.status = IncidentStatus.RESOLVED;
    }
}