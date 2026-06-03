package drsinitial.model;

import drsinitial.model.enums.IncidentStatus;
import java.time.LocalDateTime;

/**
 * Represents an emergency response operation in the
 * Disaster Response System.
 *
 * This class stores emergency dispatch details including:
 * linked incident, assigned response agency, assigned
 * emergency resource, dispatch notes, dispatch date and
 * time, and response status.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class EmergencyResponse {

    /**
     * Unique response identifier.
     */
    private String responseId;

    /**
     * Linked incident for the response.
     */
    private Incident incident;

    /**
     * Assigned response agency.
     */
    private ResponseAgency responseAgency;

    /**
     * Assigned emergency resource.
     */
    private EmergencyResource emergencyResource;

    /**
     * Dispatch notes and response details.
     */
    private String dispatchNotes;

    /**
     * Date and time of emergency dispatch.
     */
    private LocalDateTime dispatchDateTime;

    /**
     * Current response status.
     */
    private IncidentStatus responseStatus;

    /**
     * Creates a default emergency response.
     *
     * Default status is DISPATCHED.
     */
    public EmergencyResponse() {
        this.dispatchDateTime = LocalDateTime.now();
        this.responseStatus = IncidentStatus.DISPATCHED;
    }

    /**
     * Creates an emergency response with complete details.
     *
     * @param responseId unique response identifier
     * @param incident linked incident
     * @param responseAgency assigned response agency
     * @param emergencyResource assigned emergency resource
     * @param dispatchNotes dispatch notes
     */
    public EmergencyResponse(String responseId,
            Incident incident,
            ResponseAgency responseAgency,
            EmergencyResource emergencyResource,
            String dispatchNotes) {

        this.responseId = responseId;
        this.incident = incident;
        this.responseAgency = responseAgency;
        this.emergencyResource = emergencyResource;
        this.dispatchNotes = dispatchNotes;
        this.dispatchDateTime = LocalDateTime.now();
        this.responseStatus = IncidentStatus.DISPATCHED;
    }

    /**
     * Dispatches the emergency response.
     *
     * The response is successful only when the response agency
     * is available and one emergency resource unit is available.
     *
     * @return true if dispatch succeeds, otherwise false
     */
    public boolean dispatchResponse() {

        if (this.responseAgency == null
                || this.emergencyResource == null
                || !this.responseAgency.checkAvailability()
                || !this.emergencyResource.checkAvailability()) {
            return false;
        }

        boolean assigned = this.emergencyResource.assignResource();

        if (!assigned) {
            return false;
        }

        this.responseStatus = IncidentStatus.DISPATCHED;
        this.dispatchDateTime = LocalDateTime.now();

        if (this.incident != null) {
            this.incident.updateStatus(IncidentStatus.DISPATCHED);
        }

        return true;
    }

    /**
     * Completes the emergency response.
     *
     * Response status becomes RESOLVED and one assigned
     * emergency resource unit is released.
     */
    public void completeResponse() {

        this.responseStatus = IncidentStatus.RESOLVED;

        if (this.incident != null) {
            this.incident.updateStatus(IncidentStatus.RESOLVED);
        }

        if (this.emergencyResource != null) {
            this.emergencyResource.releaseResource();
        }
    }

    /**
     * Returns the response identifier.
     *
     * @return response identifier
     */
    public String getResponseId() {
        return this.responseId;
    }

    /**
     * Updates the response identifier.
     *
     * @param responseId response identifier
     */
    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    /**
     * Returns the linked incident.
     *
     * @return linked incident
     */
    public Incident getIncident() {
        return this.incident;
    }

    /**
     * Updates the linked incident.
     *
     * @param incident linked incident
     */
    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    /**
     * Returns the response agency.
     *
     * @return response agency
     */
    public ResponseAgency getResponseAgency() {
        return this.responseAgency;
    }

    /**
     * Updates the response agency.
     *
     * @param responseAgency assigned response agency
     */
    public void setResponseAgency(ResponseAgency responseAgency) {
        this.responseAgency = responseAgency;
    }

    /**
     * Returns the emergency resource.
     *
     * @return emergency resource
     */
    public EmergencyResource getEmergencyResource() {
        return this.emergencyResource;
    }

    /**
     * Updates the emergency resource.
     *
     * @param emergencyResource assigned emergency resource
     */
    public void setEmergencyResource(
            EmergencyResource emergencyResource) {

        this.emergencyResource = emergencyResource;
    }

    /**
     * Returns the dispatch notes.
     *
     * @return dispatch notes
     */
    public String getDispatchNotes() {
        return this.dispatchNotes;
    }

    /**
     * Updates the dispatch notes.
     *
     * @param dispatchNotes dispatch notes
     */
    public void setDispatchNotes(String dispatchNotes) {
        this.dispatchNotes = dispatchNotes;
    }

    /**
     * Returns the dispatch date and time.
     *
     * @return dispatch date and time
     */
    public LocalDateTime getDispatchDateTime() {
        return this.dispatchDateTime;
    }

    /**
     * Updates the dispatch date and time.
     *
     * @param dispatchDateTime dispatch date and time
     */
    public void setDispatchDateTime(
            LocalDateTime dispatchDateTime) {

        this.dispatchDateTime = dispatchDateTime;
    }

    /**
     * Returns the response status.
     *
     * @return response status
     */
    public IncidentStatus getResponseStatus() {
        return this.responseStatus;
    }

    /**
     * Updates the response status.
     *
     * @param responseStatus updated response status
     */
    public void setResponseStatus(
            IncidentStatus responseStatus) {

        this.responseStatus = responseStatus;
    }

    /**
     * Returns the formatted response summary.
     *
     * @return formatted response string
     */
    @Override
    public String toString() {

        return this.responseId
                + " - "
                + this.responseStatus
                + " - "
                + this.dispatchDateTime;
    }
}