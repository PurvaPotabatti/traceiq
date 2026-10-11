package io.traceiq.core.storage;

import io.traceiq.core.model.AnalysisResult;
import io.traceiq.core.model.Evidence;
import io.traceiq.core.model.Incident;
import io.traceiq.core.model.IncidentOccurrence;

import java.util.List;
import java.util.Optional;

public interface IncidentRepository {

    void saveIncident(Incident incident);

    Optional<Incident> findIncidentById(String incidentId);

    List<Incident> findAllIncidents();

    void saveOccurrence(IncidentOccurrence occurrence);

    List<IncidentOccurrence> findOccurrencesByIncidentId(
            String incidentId);

    void saveEvidence(Evidence evidence);

    List<Evidence> findEvidenceByIncidentId(String incidentId);

    void saveAnalysisResult(AnalysisResult result);

    List<AnalysisResult> findAnalysisResultsByIncidentId(
            String incidentId);
}
