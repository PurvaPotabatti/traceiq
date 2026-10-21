package io.traceiq.storage;

import io.traceiq.core.model.AnalysisResult;
import io.traceiq.core.model.Evidence;
import io.traceiq.core.model.Incident;
import io.traceiq.core.model.IncidentOccurrence;
import io.traceiq.core.storage.IncidentRepository;
import io.traceiq.storage.entity.AnalysisResultEntity;
import io.traceiq.storage.entity.EvidenceEntity;
import io.traceiq.storage.entity.IncidentEntity;
import io.traceiq.storage.entity.IncidentOccurrenceEntity;
import io.traceiq.storage.mapper.TraceIqEntityMapper;
import io.traceiq.storage.repository.AnalysisResultJpaRepository;
import io.traceiq.storage.repository.EvidenceJpaRepository;
import io.traceiq.storage.repository.IncidentJpaRepository;
import io.traceiq.storage.repository.IncidentOccurrenceJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class IncidentRepositoryImpl implements IncidentRepository {

    private final IncidentJpaRepository incidentRepository;
    private final IncidentOccurrenceJpaRepository occurrenceRepository;
    private final EvidenceJpaRepository evidenceRepository;
    private final AnalysisResultJpaRepository analysisResultRepository;

    public IncidentRepositoryImpl(
            IncidentJpaRepository incidentRepository,
            IncidentOccurrenceJpaRepository occurrenceRepository,
            EvidenceJpaRepository evidenceRepository,
            AnalysisResultJpaRepository analysisResultRepository) {

        this.incidentRepository = incidentRepository;
        this.occurrenceRepository = occurrenceRepository;
        this.evidenceRepository = evidenceRepository;
        this.analysisResultRepository = analysisResultRepository;
    }

    @Override
    public void saveIncident(Incident incident) {
        IncidentEntity entity =
                TraceIqEntityMapper.toEntity(incident);

        incidentRepository.save(entity);
    }

    @Override
    public Optional<Incident> findIncidentById(String incidentId) {
        return incidentRepository.findById(incidentId)
                .map(TraceIqEntityMapper::toModel);
    }

    @Override
    public Optional<Incident> findIncidentByFingerprint(
            String fingerprint) {

        return incidentRepository
                .findByFingerprint(fingerprint)
                .map(TraceIqEntityMapper::toModel);
    }

    @Override
    public List<Incident> findAllIncidents() {
        return incidentRepository.findAll()
                .stream()
                .map(TraceIqEntityMapper::toModel)
                .toList();
    }

    @Override
    public void saveOccurrence(
            IncidentOccurrence occurrence) {

        IncidentOccurrenceEntity entity =
                TraceIqEntityMapper.toEntity(occurrence);

        occurrenceRepository.save(entity);
    }

    @Override
    public List<IncidentOccurrence>
    findOccurrencesByIncidentId(String incidentId) {

        return occurrenceRepository
                .findByIncidentId(incidentId)
                .stream()
                .map(TraceIqEntityMapper::toModel)
                .toList();
    }

    @Override
    public void saveEvidence(Evidence evidence) {
        EvidenceEntity entity =
                TraceIqEntityMapper.toEntity(evidence);

        evidenceRepository.save(entity);
    }

    @Override
    public List<Evidence>
    findEvidenceByIncidentId(String incidentId) {

        return evidenceRepository
                .findByIncidentId(incidentId)
                .stream()
                .map(TraceIqEntityMapper::toModel)
                .toList();
    }

    @Override
    public void saveAnalysisResult(
            AnalysisResult result) {

        AnalysisResultEntity entity =
                TraceIqEntityMapper.toEntity(result);

        analysisResultRepository.save(entity);
    }

    @Override
    public List<AnalysisResult>
    findAnalysisResultsByIncidentId(String incidentId) {

        return analysisResultRepository
                .findByIncidentId(incidentId)
                .stream()
                .map(TraceIqEntityMapper::toModel)
                .toList();
    }
}