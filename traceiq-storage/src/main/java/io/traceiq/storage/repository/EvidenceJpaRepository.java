package io.traceiq.storage.repository;

import io.traceiq.storage.entity.EvidenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvidenceJpaRepository
        extends JpaRepository<EvidenceEntity, String> {

    List<EvidenceEntity> findByIncidentId(String incidentId);
}