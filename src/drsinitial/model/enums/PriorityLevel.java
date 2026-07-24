package drsinitial.model.enums;

/**
 * Defines the priority levels used for disaster incident response.
 *
 * This enum is used to classify how urgently an incident
 * should be handled by the Disaster Response System.
 *
 * @author Jerald Christopher Bucud
 */
public enum PriorityLevel {

    /**
     * Low-priority incident with minimal impact or urgency.
     */
    LOW,

    /**
     * Medium-priority incident requiring timely response.
     */
    MEDIUM,

    /**
     * High-priority incident requiring urgent response.
     */
    HIGH,

    /**
     * Emergency incident requiring immediate response.
     */
    EMERGENCY
}