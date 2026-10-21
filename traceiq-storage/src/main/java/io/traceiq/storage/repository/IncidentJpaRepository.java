package io.traceiq.storage.repository;

import io.traceiq.storage.entity.IncidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IncidentJpaRepository
        extends JpaRepository<IncidentEntity, String> {

    Optional<IncidentEntity> findByFingerprint(String fingerprint);
}