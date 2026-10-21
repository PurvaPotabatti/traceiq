package io.traceiq.storage.repository;

import io.traceiq.storage.entity.IncidentOccurrenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentOccurrenceJpaRepository
        extends JpaRepository<IncidentOccurrenceEntity, String> {

    List<IncidentOccurrenceEntity> findByIncidentId(String incidentId);
}