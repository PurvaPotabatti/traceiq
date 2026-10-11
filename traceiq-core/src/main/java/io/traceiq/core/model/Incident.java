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
}
