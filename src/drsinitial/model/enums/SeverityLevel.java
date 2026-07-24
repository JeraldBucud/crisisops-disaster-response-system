package drsinitial.model.enums;

/**
 * Defines the severity levels used for disaster reports and incidents.
 *
 * This enum is used to classify the impact level of a disaster
 * before or during incident assessment in the Disaster Response System.
 *
 * @author Jerald Christopher Bucud
 */
public enum SeverityLevel {

    /**
     * Low-impact disaster with limited risk or damage.
     */
    LOW,

    /**
     * Medium-impact disaster requiring planned response.
     */
    MEDIUM,

    /**
     * High-impact disaster requiring urgent assessment and response.
     */
    HIGH,

    /**
     * Critical disaster requiring immediate assessment and response.
     */
    CRITICAL
}