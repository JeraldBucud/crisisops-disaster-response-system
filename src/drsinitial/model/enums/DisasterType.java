package drsinitial.model.enums;

/**
 * Defines the disaster categories supported by the
 * Disaster Response System.
 *
 * This enum is used when creating disaster reports,
 * registering incidents, filtering incidents, and
 * coordinating emergency responses.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public enum DisasterType {

    /**
     * Fire-related disaster.
     */
    FIRE,

    /**
     * Flood-related disaster.
     */
    FLOOD,

    /**
     * Earthquake-related disaster.
     */
    EARTHQUAKE,

    /**
     * Hurricane-related disaster.
     */
    HURRICANE,

    /**
     * Landslide-related disaster.
     */
    LANDSLIDE,

    /**
     * Storm-related disaster.
     */
    STORM
}