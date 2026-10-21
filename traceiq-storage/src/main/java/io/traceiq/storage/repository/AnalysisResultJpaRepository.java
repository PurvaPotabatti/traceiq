package io.traceiq.storage.repository;

import io.traceiq.storage.entity.AnalysisResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnalysisResultJpaRepository
        extends JpaRepository<AnalysisResultEntity, String> {

    List<AnalysisResultEntity> findByIncidentId(String incidentId);
}