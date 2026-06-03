package drsinitial.model.enums;

/**
 * Defines the availability status values for emergency resources.
 *
 * This enum is used to track whether a response resource
 * is available, already assigned, unavailable, or under
 * maintenance in the Disaster Response System.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public enum ResourceStatus {

    /**
     * Resource is available for emergency response assignment.
     */
    AVAILABLE,

    /**
     * Resource has already been assigned to an incident.
     */
    ASSIGNED,

    /**
     * Resource is not available for response use.
     */
    UNAVAILABLE,

    /**
     * Resource is under maintenance and cannot be assigned.
     */
    UNDER_MAINTENANCE
}