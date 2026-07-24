package drsinitial.model;

import drsinitial.model.enums.IncidentStatus;
import drsinitial.model.enums.PriorityLevel;
import drsinitial.model.enums.SeverityLevel;

/**
 * Represents an incident created from a disaster report.
 *
 * This class stores incident details including:
 * incident ID, linked report ID, severity, priority,
 * status, affected people, and affected area.
 *
 * @author Jerald Christopher Bucud
 */
public class Incident {

    private String incidentId;
    private String reportId;
    private SeverityLevel severityLevel;
    private PriorityLevel priorityLevel;
    private IncidentStatus incidentStatus;
    private int affectedPeople;
    private String affectedArea;

    /**
     * Creates a default incident.
     *
     * Default incident status is REGISTERED.
     */
    public Incident() {
        this.incidentStatus = IncidentStatus.REGISTERED;
    }

    /**
     * Creates an incident with required incident details.
     *
     * @param incidentId unique incident identifier
     * @param reportId linked disaster report identifier
     * @param affectedPeople number of affected people
     * @param affectedArea affected location or area
     */
    public Incident(String incidentId,
            String reportId,
            int affectedPeople,
            String affectedArea) {

        this.incidentId = incidentId;
        this.reportId = reportId;
        this.affectedPeople = affectedPeople;
        this.affectedArea = affectedArea;
        this.incidentStatus = IncidentStatus.REGISTERED;
    }

    /**
     * Assigns the severity level of the incident.
     *
     * The incident status becomes ASSESSED.
     *
     * @param severityLevel assessed severity level
     */
    public void assessSeverity(SeverityLevel severityLevel) {
        this.severityLevel = severityLevel;
        this.incidentStatus = IncidentStatus.ASSESSED;
    }

    /**
     * Updates the priority level of the incident.
     *
     * The incident status becomes PRIORITISED.
     *
     * @param priorityLevel assigned priority level
     */
    public void updatePriority(PriorityLevel priorityLevel) {
        this.priorityLevel = priorityLevel;
        this.incidentStatus = IncidentStatus.PRIORITISED;
    }

    /**
     * Updates the incident status.
     *
     * @param incidentStatus updated incident status
     */
    public void updateStatus(IncidentStatus incidentStatus) {
        this.incidentStatus = incidentStatus;
    }

    /**
     * Returns a display value for severity.
     *
     * @return Pending if severity has not been assessed,
     * otherwise the severity value
     */
    public String getSeverityDisplay() {
        if (this.severityLevel == null) {
            return "Pending";
        }

        return this.severityLevel.toString();
    }

    public String getIncidentId() {
        return this.incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getReportId() {
        return this.reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public SeverityLevel getSeverityLevel() {
        return this.severityLevel;
    }

    public void setSeverityLevel(SeverityLevel severityLevel) {
        this.severityLevel = severityLevel;
    }

    public PriorityLevel getPriorityLevel() {
        return this.priorityLevel;
    }

    public void setPriorityLevel(PriorityLevel priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public IncidentStatus getIncidentStatus() {
        return this.incidentStatus;
    }

    public void setIncidentStatus(IncidentStatus incidentStatus) {
        this.incidentStatus = incidentStatus;
    }

    public int getAffectedPeople() {
        return this.affectedPeople;
    }

    public void setAffectedPeople(int affectedPeople) {
        this.affectedPeople = affectedPeople;
    }

    public String getAffectedArea() {
        return this.affectedArea;
    }

    public void setAffectedArea(String affectedArea) {
        this.affectedArea = affectedArea;
    }

    @Override
    public String toString() {
        return this.incidentId + " - Report " + this.reportId
                + " - " + this.affectedArea + " ("
                + this.incidentStatus + ")";
    }
}