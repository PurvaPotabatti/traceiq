package io.traceiq.storage.entity;

import io.traceiq.core.model.IncidentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "traceiq_incident")
public class IncidentEntity {

    @Id
    @Column(nullable = false, updatable = false, length = 36)
    private String id;

    @Column(nullable = false, unique = true, length = 255)
    private String fingerprint;

    @Column(name = "exception_type", nullable = false, length = 500)
    private String exceptionType;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(name = "first_seen_at", nullable = false)
    private Instant firstSeenAt;

    @Column(name = "last_seen_at", nullable = false)
    private Instant lastSeenAt;

    @Column(name = "occurrence_count", nullable = false)
    private long occurrenceCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private IncidentStatus status;

    protected IncidentEntity() {
        // Required by JPA
    }

    public IncidentEntity(
            String id,
            String fingerprint,
            String exceptionType,
            String message,
            Instant firstSeenAt,
            Instant lastSeenAt,
            long occurrenceCount,
            IncidentStatus status) {

        this.id = id;
        this.fingerprint = fingerprint;
        this.exceptionType = exceptionType;
        this.message = message;
        this.firstSeenAt = firstSeenAt;
        this.lastSeenAt = lastSeenAt;
        this.occurrenceCount = occurrenceCount;
        this.status = status;
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
}