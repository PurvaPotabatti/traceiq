package io.traceiq.core.service;

import io.traceiq.core.model.Incident;
import io.traceiq.core.model.IncidentOccurrence;

import java.util.List;
import java.util.Optional;

public interface IncidentService {

    Incident recordIncident(
            String exceptionType,
            String message,
            String stackTrace);

    Optional<Incident> findIncidentById(String incidentId);

    List<Incident> findAllIncidents();

    List<IncidentOccurrence> findOccurrences(
            String incidentId);
}