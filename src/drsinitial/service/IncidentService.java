package drsinitial.service;

import drsinitial.model.Incident;
import drsinitial.model.enums.IncidentStatus;
import drsinitial.model.enums.PriorityLevel;
import drsinitial.model.enums.SeverityLevel;
import drsinitial.repository.ApplicationRepository;
import javafx.collections.ObservableList;

/**
 * Manages incident-related business logic for the Disaster Response System.
 *
 * This service separates incident processing logic from JavaFX controllers.
 * It uses ApplicationRepository as the central in-memory data source.
 *
 * @author Jerald Christopher Bucud
 */
public class IncidentService {

    /**
     * Creates an IncidentService object.
     */
    public IncidentService() {

    }

    /**
     * Adds an incident to the repository.
     *
     * @param incident the incident to add
     * @return true if the incident was added, otherwise false
     */
    public boolean addIncident(Incident incident) {
        if (incident == null) {
            return false;
        }

        ApplicationRepository.addIncident(incident);
        return true;
    }

    /**
     * Creates and registers a new incident.
     *
     * @param incidentId the incident ID
     * @param reportId the linked report ID
     * @param affectedPeople the number of affected people
     * @param affectedArea the affected area
     * @return the created incident
     */
    public Incident registerIncident(
            String incidentId,
            String reportId,
            int affectedPeople,
            String affectedArea
    ) {
        Incident incident = new Incident(
                incidentId,
                reportId,
                affectedPeople,
                affectedArea
        );

        incident.updateStatus(IncidentStatus.REGISTERED);
        ApplicationRepository.addIncident(incident);

        return incident;
    }

    /**
     * Assesses the severity level of an incident.
     *
     * @param incident the incident to assess
     * @param severityLevel the assigned severity level
     * @return true if the incident was assessed, otherwise false
     */
    public boolean assessIncident(
            Incident incident,
            SeverityLevel severityLevel
    ) {
        if (incident == null || severityLevel == null) {
            return false;
        }

        incident.assessSeverity(severityLevel);
        return true;
    }

    /**
     * Updates the priority level of an incident.
     *
     * @param incident the incident to prioritise
     * @param priorityLevel the assigned priority level
     * @return true if the priority was updated, otherwise false
     */
    public boolean prioritiseIncident(
            Incident incident,
            PriorityLevel priorityLevel
    ) {
        if (incident == null || priorityLevel == null) {
            return false;
        }

        incident.updatePriority(priorityLevel);
        return true;
    }

    /**
     * Updates the status of an incident.
     *
     * @param incident the incident to update
     * @param incidentStatus the new incident status
     * @return true if the status was updated, otherwise false
     */
    public boolean updateIncidentStatus(
            Incident incident,
            IncidentStatus incidentStatus
    ) {
        if (incident == null || incidentStatus == null) {
            return false;
        }

        incident.updateStatus(incidentStatus);
        return true;
    }

    /**
     * Marks an incident as dispatched.
     *
     * @param incident the incident to dispatch
     * @return true if the status was updated, otherwise false
     */
    public boolean dispatchIncident(Incident incident) {
        return updateIncidentStatus(incident, IncidentStatus.DISPATCHED);
    }

    /**
     * Marks an incident as ongoing.
     *
     * @param incident the incident to mark as ongoing
     * @return true if the status was updated, otherwise false
     */
    public boolean markIncidentOngoing(Incident incident) {
        return updateIncidentStatus(incident, IncidentStatus.ONGOING);
    }

    /**
     * Marks an incident as resolved.
     *
     * @param incident the incident to resolve
     * @return true if the status was updated, otherwise false
     */
    public boolean resolveIncident(Incident incident) {
        return updateIncidentStatus(incident, IncidentStatus.RESOLVED);
    }

    /**
     * Marks an incident as closed.
     *
     * @param incident the incident to close
     * @return true if the status was updated, otherwise false
     */
    public boolean closeIncident(Incident incident) {
        return updateIncidentStatus(incident, IncidentStatus.CLOSED);
    }

    /**
     * Marks an incident as rejected.
     *
     * @param incident the incident to reject
     * @return true if the status was updated, otherwise false
     */
    public boolean rejectIncident(Incident incident) {
        return updateIncidentStatus(incident, IncidentStatus.REJECTED);
    }

    /**
     * Returns all incidents from the repository.
     *
     * @return observable list of all incidents
     */
    public ObservableList<Incident> getAllIncidents() {
        return ApplicationRepository.getIncidents();
    }

    /**
     * Returns incidents that still require action.
     *
     * Resolved, closed, and rejected incidents are excluded.
     *
     * @return observable list of active incidents
     */
    public ObservableList<Incident> getActiveIncidents() {
        ObservableList<Incident> activeIncidents =
                javafx.collections.FXCollections.observableArrayList();

        for (Incident incident : ApplicationRepository.getIncidents()) {
            IncidentStatus status = incident.getIncidentStatus();

            if (status != IncidentStatus.RESOLVED
                    && status != IncidentStatus.CLOSED
                    && status != IncidentStatus.REJECTED) {

                activeIncidents.add(incident);
            }
        }

        return activeIncidents;
    }

    /**
     * Returns resolved incidents.
     *
     * @return observable list of resolved incidents
     */
    public ObservableList<Incident> getResolvedIncidents() {
        ObservableList<Incident> resolvedIncidents =
                javafx.collections.FXCollections.observableArrayList();

        for (Incident incident : ApplicationRepository.getIncidents()) {
            if (incident.getIncidentStatus() == IncidentStatus.RESOLVED) {
                resolvedIncidents.add(incident);
            }
        }

        return resolvedIncidents;
    }

    /**
     * Returns the total number of incidents.
     *
     * @return total incident count
     */
    public int getTotalIncidentCount() {
        return ApplicationRepository.getIncidents().size();
    }

    /**
     * Returns the number of active incidents.
     *
     * @return active incident count
     */
    public int getActiveIncidentCount() {
        return getActiveIncidents().size();
    }

    /**
     * Returns the number of resolved incidents.
     *
     * @return resolved incident count
     */
    public int getResolvedIncidentCount() {
        return getResolvedIncidents().size();
    }

    /**
     * Clears all incidents from the repository.
     */
    public void clearIncidents() {
        ApplicationRepository.getIncidents().clear();
    }
}