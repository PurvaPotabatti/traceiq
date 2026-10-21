package io.traceiq.storage;

import io.traceiq.core.model.AnalysisResult;
import io.traceiq.core.model.AnalysisStatus;
import io.traceiq.core.model.Evidence;
import io.traceiq.core.model.EvidenceType;
import io.traceiq.core.model.Incident;
import io.traceiq.core.model.IncidentOccurrence;
import io.traceiq.core.storage.IncidentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.annotation.DirtiesContext;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = StoragePersistenceTestConfig.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

class IncidentRepositoryPersistenceTest {

    @Autowired
    private IncidentRepository incidentRepository;

    @Test
    void shouldSaveAndRetrieveIncident() {

        Instant now = Instant.now();

        Incident incident = new Incident(
                "fingerprint-123",
                "java.lang.NullPointerException",
                "Something went wrong",
                now
        );

        incidentRepository.saveIncident(incident);

        var retrieved =
                incidentRepository.findIncidentById(incident.getId());

        assertTrue(retrieved.isPresent());

        Incident result = retrieved.get();

        assertEquals(incident.getId(), result.getId());
        assertEquals(incident.getFingerprint(), result.getFingerprint());
        assertEquals(incident.getExceptionType(), result.getExceptionType());
        assertEquals(incident.getMessage(), result.getMessage());
        assertEquals(incident.getFirstSeenAt(), result.getFirstSeenAt());
        assertEquals(incident.getLastSeenAt(), result.getLastSeenAt());
        assertEquals(incident.getOccurrenceCount(), result.getOccurrenceCount());
        assertEquals(incident.getStatus(), result.getStatus());
    }

    @Test
    void shouldFindIncidentByFingerprint() {

        Incident incident = new Incident(
                "unique-fingerprint-456",
                "java.lang.IllegalStateException",
                "Invalid state",
                Instant.now()
        );

        incidentRepository.saveIncident(incident);

        var retrieved =
                incidentRepository.findIncidentByFingerprint(
                        "unique-fingerprint-456"
                );

        assertTrue(retrieved.isPresent());
        assertEquals(
                incident.getId(),
                retrieved.get().getId()
        );
    }

    @Test
    void shouldFindAllIncidents() {

        Incident first = new Incident(
                "fingerprint-a",
                "ExceptionA",
                "First",
                Instant.now()
        );

        Incident second = new Incident(
                "fingerprint-b",
                "ExceptionB",
                "Second",
                Instant.now()
        );

        incidentRepository.saveIncident(first);
        incidentRepository.saveIncident(second);

        List<Incident> incidents =
                incidentRepository.findAllIncidents();

        assertEquals(2, incidents.size());
    }

    @Test
    void shouldSaveMultipleOccurrencesForSameIncident() {

        Incident incident = new Incident(
                "fingerprint-occurrence",
                "java.lang.RuntimeException",
                "Repeated failure",
                Instant.now()
        );

        incidentRepository.saveIncident(incident);

        IncidentOccurrence firstOccurrence =
                new IncidentOccurrence(
                        incident.getId(),
                        Instant.now(),
                        "stack trace 1"
                );

        IncidentOccurrence secondOccurrence =
                new IncidentOccurrence(
                        incident.getId(),
                        Instant.now().plusSeconds(10),
                        "stack trace 2"
                );

        incidentRepository.saveOccurrence(firstOccurrence);
        incidentRepository.saveOccurrence(secondOccurrence);

        List<IncidentOccurrence> occurrences =
                incidentRepository.findOccurrencesByIncidentId(
                        incident.getId()
                );

        assertEquals(2, occurrences.size());

        assertTrue(
                occurrences.stream()
                        .allMatch(o ->
                                o.getIncidentId()
                                        .equals(incident.getId()))
        );
    }

    @Test
    void shouldSaveAndRetrieveEvidence() {

        Incident incident = new Incident(
                "fingerprint-evidence",
                "java.lang.Exception",
                "Evidence test",
                Instant.now()
        );

        incidentRepository.saveIncident(incident);

        Instant collectedAt = Instant.parse(
                "2026-10-21T15:13:32.653Z"
        );

        Evidence evidence = new Evidence(
                incident.getId(),
                null,
                EvidenceType.STACK_TRACE,
                "Example stack trace",
                collectedAt
        );

        incidentRepository.saveEvidence(evidence);

        List<Evidence> results =
                incidentRepository.findEvidenceByIncidentId(
                        incident.getId()
                );

        assertEquals(1, results.size());

        Evidence retrieved = results.get(0);

        assertEquals(evidence.getId(), retrieved.getId());
        assertEquals(evidence.getIncidentId(), retrieved.getIncidentId());
        assertEquals(evidence.getType(), retrieved.getType());
        assertEquals(evidence.getContent(), retrieved.getContent());
        assertEquals(
                evidence.getCollectedAt().toEpochMilli(),
                retrieved.getCollectedAt().toEpochMilli()
        );
    }

    @Test
    void shouldSaveAndRetrieveAnalysisResult() {

        Incident incident = new Incident(
                "fingerprint-analysis",
                "java.lang.Exception",
                "Analysis test",
                Instant.now()
        );

        incidentRepository.saveIncident(incident);

        AnalysisResult analysisResult =
                new AnalysisResult(
                        incident.getId(),
                        "Database connection failure",
                        "The application could not connect to the database.",
                        "Check database availability and configuration.",
                        "rule-based",
                        0.95,
                        Instant.now(),
                        AnalysisStatus.SUCCESS,
                        null
                );

        incidentRepository.saveAnalysisResult(analysisResult);

        List<AnalysisResult> results =
                incidentRepository.findAnalysisResultsByIncidentId(
                        incident.getId()
                );

        assertEquals(1, results.size());

        AnalysisResult retrieved = results.get(0);

        assertEquals(
                analysisResult.getId(),
                retrieved.getId()
        );

        assertEquals(
                analysisResult.getIncidentId(),
                retrieved.getIncidentId()
        );

        assertEquals(
                analysisResult.getRootCause(),
                retrieved.getRootCause()
        );

        assertEquals(
                analysisResult.getExplanation(),
                retrieved.getExplanation()
        );

        assertEquals(
                analysisResult.getSuggestedFix(),
                retrieved.getSuggestedFix()
        );

        assertEquals(
                analysisResult.getProvider(),
                retrieved.getProvider()
        );

        assertEquals(
                analysisResult.getConfidenceScore(),
                retrieved.getConfidenceScore()
        );

        assertEquals(
                analysisResult.getStatus(),
                retrieved.getStatus()
        );
    }

    @Test
    void shouldReturnEmptyWhenIncidentDoesNotExist() {

        assertTrue(
                incidentRepository
                        .findIncidentById("does-not-exist")
                        .isEmpty()
        );
    }

    @Test
    void shouldReturnEmptyCollectionsWhenRelatedRecordsDoNotExist() {

        String nonexistentIncidentId = "does-not-exist";

        assertTrue(
                incidentRepository
                        .findOccurrencesByIncidentId(
                                nonexistentIncidentId
                        )
                        .isEmpty()
        );

        assertTrue(
                incidentRepository
                        .findEvidenceByIncidentId(
                                nonexistentIncidentId
                        )
                        .isEmpty()
        );

        assertTrue(
                incidentRepository
                        .findAnalysisResultsByIncidentId(
                                nonexistentIncidentId
                        )
                        .isEmpty()
        );
    }
}