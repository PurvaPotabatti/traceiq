package io.traceiq.core.service.impl;

import io.traceiq.core.fingerprint.FingerprintGenerator;
import io.traceiq.core.model.Incident;
import io.traceiq.core.model.IncidentOccurrence;
import io.traceiq.core.service.IncidentService;
import io.traceiq.core.storage.IncidentRepository;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DefaultIncidentService implements IncidentService {

    private final IncidentRepository incidentRepository;
    private final FingerprintGenerator fingerprintGenerator;

    public DefaultIncidentService(
            IncidentRepository incidentRepository,
            FingerprintGenerator fingerprintGenerator) {

        this.incidentRepository = Objects.requireNonNull(
                incidentRepository,
                "incidentRepository must not be null");

        this.fingerprintGenerator = Objects.requireNonNull(
                fingerprintGenerator,
                "fingerprintGenerator must not be null");
    }

    @Override
    public Incident recordIncident(
            String exceptionType,
            String message,
            String stackTrace) {

        Objects.requireNonNull(
                exceptionType,
                "exceptionType must not be null");

        Objects.requireNonNull(
                stackTrace,
                "stackTrace must not be null");

        String fingerprint =
                fingerprintGenerator.generate(
                        exceptionType,
                        stackTrace);

        Optional<Incident> existingIncident =
                findIncidentByFingerprint(fingerprint);

        if (existingIncident.isPresent()) {

            Incident incident = existingIncident.get();

            IncidentOccurrence occurrence =
                    new IncidentOccurrence(
                            incident.getId(),
                            Instant.now(),
                            stackTrace);

            incidentRepository.saveOccurrence(occurrence);

            return incident;
        }

        Incident incident = new Incident(
                fingerprint,
                exceptionType,
                message,
                Instant.now());

        incidentRepository.saveIncident(incident);

        IncidentOccurrence occurrence =
                new IncidentOccurrence(
                        incident.getId(),
                        Instant.now(),
                        stackTrace);

        incidentRepository.saveOccurrence(occurrence);

        return incident;
    }

    @Override
    public Optional<Incident> findIncidentById(
            String incidentId) {

        Objects.requireNonNull(
                incidentId,
                "incidentId must not be null");

        return incidentRepository.findIncidentById(incidentId);
    }

    @Override
    public List<Incident> findAllIncidents() {
        return incidentRepository.findAllIncidents();
    }

    @Override
    public List<IncidentOccurrence> findOccurrences(
            String incidentId) {

        Objects.requireNonNull(
                incidentId,
                "incidentId must not be null");

        return incidentRepository
                .findOccurrencesByIncidentId(incidentId);
    }

    private Optional<Incident> findIncidentByFingerprint(
            String fingerprint) {

        return incidentRepository
                .findAllIncidents()
                .stream()
                .filter(incident ->
                        incident.getFingerprint()
                                .equals(fingerprint))
                .findFirst();
    }
}