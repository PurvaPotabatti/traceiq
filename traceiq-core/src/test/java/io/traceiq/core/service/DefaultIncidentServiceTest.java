package io.traceiq.core.service;

import io.traceiq.core.fingerprint.FingerprintGenerator;
import io.traceiq.core.model.Incident;
import io.traceiq.core.model.IncidentOccurrence;
import io.traceiq.core.storage.IncidentRepository;
import io.traceiq.core.service.impl.DefaultIncidentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DefaultIncidentServiceTest {

    private IncidentRepository incidentRepository;
    private FingerprintGenerator fingerprintGenerator;
    private DefaultIncidentService service;

    @BeforeEach
    void setUp() {
        incidentRepository = mock(IncidentRepository.class);
        fingerprintGenerator = mock(FingerprintGenerator.class);

        service = new DefaultIncidentService(
                incidentRepository,
                fingerprintGenerator);
    }

    @Test
    void shouldCreateNewIncidentWhenFingerprintDoesNotExist() {

        String exceptionType = "java.lang.NullPointerException";
        String message = "Something went wrong";
        String stackTrace = "stack trace here";
        String fingerprint = "fingerprint-123";

        when(fingerprintGenerator.generate(
                exceptionType,
                stackTrace))
                .thenReturn(fingerprint);

        when(incidentRepository.findIncidentByFingerprint(
                fingerprint))
                .thenReturn(Optional.empty());

        Incident result = service.recordIncident(
                exceptionType,
                message,
                stackTrace);

        assertNotNull(result);
        assertEquals(fingerprint, result.getFingerprint());
        assertEquals(exceptionType, result.getExceptionType());
        assertEquals(message, result.getMessage());

        verify(incidentRepository)
                .saveIncident(result);

        verify(incidentRepository)
                .saveOccurrence(any(IncidentOccurrence.class));
    }

    @Test
    void shouldCreateOccurrenceForExistingIncident() {

        String exceptionType = "java.lang.NullPointerException";
        String message = "Something went wrong";
        String stackTrace = "same stack trace";
        String fingerprint = "fingerprint-123";

        Incident existingIncident = new Incident(
                fingerprint,
                exceptionType,
                message,
                Instant.now());

        when(fingerprintGenerator.generate(
                exceptionType,
                stackTrace))
                .thenReturn(fingerprint);

        when(incidentRepository.findIncidentByFingerprint(
                fingerprint))
                .thenReturn(Optional.of(existingIncident));

        Incident result = service.recordIncident(
                exceptionType,
                message,
                stackTrace);

        assertEquals(
                existingIncident.getId(),
                result.getId());

        verify(incidentRepository, never())
                .saveIncident(any(Incident.class));

        verify(incidentRepository)
                .saveOccurrence(any(IncidentOccurrence.class));
    }

    @Test
    void shouldFindIncidentById() {

        Incident incident = new Incident(
                "fingerprint-123",
                "java.lang.Exception",
                "Test",
                Instant.now());

        when(incidentRepository.findIncidentById(
                incident.getId()))
                .thenReturn(Optional.of(incident));

        Optional<Incident> result =
                service.findIncidentById(incident.getId());

        assertTrue(result.isPresent());
        assertEquals(
                incident.getId(),
                result.get().getId());

        verify(incidentRepository)
                .findIncidentById(incident.getId());
    }

    @Test
    void shouldFindAllIncidents() {

        List<Incident> incidents = List.of(
                new Incident(
                        "fp-1",
                        "ExceptionA",
                        "First",
                        Instant.now()),
                new Incident(
                        "fp-2",
                        "ExceptionB",
                        "Second",
                        Instant.now()));

        when(incidentRepository.findAllIncidents())
                .thenReturn(incidents);

        List<Incident> result =
                service.findAllIncidents();

        assertEquals(2, result.size());
        verify(incidentRepository)
                .findAllIncidents();
    }

    @Test
    void shouldFindOccurrencesForIncident() {

        String incidentId = "incident-123";

        List<IncidentOccurrence> occurrences =
                List.of(
                        new IncidentOccurrence(
                                incidentId,
                                Instant.now(),
                                "stack trace"));

        when(incidentRepository
                .findOccurrencesByIncidentId(incidentId))
                .thenReturn(occurrences);

        List<IncidentOccurrence> result =
                service.findOccurrences(incidentId);

        assertEquals(1, result.size());

        verify(incidentRepository)
                .findOccurrencesByIncidentId(incidentId);
    }
}