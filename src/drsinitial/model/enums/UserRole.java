package drsinitial.model.enums;

/**
 * Defines the user roles available in the
 * Disaster Response System.
 *
 * This enum supports role-based access control by identifying
 * the type of user currently interacting with the system.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public enum UserRole {

    /**
     * Public user who submits disaster reports.
     */
    PUBLIC_USER,

    /**
     * Emergency control centre user who manages reports,
     * incidents, assessments, priorities, and responses.
     */
    EMERGENCY_CONTROL_CENTRE,

    /**
     * System administrator who manages system-level
     * configuration and maintenance functions.
     */
    SYSTEM_ADMINISTRATOR
}