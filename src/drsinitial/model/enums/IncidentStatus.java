package drsinitial.model.enums;

/**
 * Defines the incident status values used in the
 * Disaster Response System.
 *
 * This enum tracks the life cycle of a disaster incident
 * from the first report through assessment, dispatch,
 * resolution, closure, or rejection.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public enum IncidentStatus {

    /**
     * Disaster report has been submitted but not yet reviewed.
     */
    REPORTED,

    /**
     * Disaster report has been validated and registered as an incident.
     */
    REGISTERED,

    /**
     * Incident has been assessed for severity and impact.
     */
    ASSESSED,

    /**
     * Incident has been assigned a response priority.
     */
    PRIORITISED,

    /**
     * Emergency response has been dispatched.
     */
    DISPATCHED,

    /**
     * Incident response is currently active.
     */
    ONGOING,

    /**
     * Incident has been resolved.
     */
    RESOLVED,

    /**
     * Incident record has been closed after completion.
     */
    CLOSED,

    /**
     * Disaster report or incident has been rejected.
     */
    REJECTED
}