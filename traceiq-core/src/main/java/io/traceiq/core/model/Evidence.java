package io.traceiq.core.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Evidence {

    private final String id;
    private final String incidentId;
    private final String type;
    private final String content;
    private final Instant collectedAt;

    public Evidence(
            String incidentId,
            String type,
            String content,
            Instant collectedAt) {

        this.id = UUID.randomUUID().toString();
        this.incidentId = Objects.requireNonNull(
                incidentId, "incidentId must not be null");
        this.type = Objects.requireNonNull(
                type, "type must not be null");
        this.content = Objects.requireNonNull(
                content, "content must not be null");
        this.collectedAt = Objects.requireNonNull(
                collectedAt, "collectedAt must not be null");
    }

    public String getId() {
        return id;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public Instant getCollectedAt() {
        return collectedAt;
    }
}
