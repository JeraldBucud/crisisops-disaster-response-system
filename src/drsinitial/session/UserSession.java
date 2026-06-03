package drsinitial.session;

import drsinitial.model.enums.UserRole;

/**
 * Stores the selected prototype user role during the running session.
 *
 * This class supports role selection for the Disaster Response System
 * prototype. It does not perform authentication or access control.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class UserSession {

    /**
     * Stores the selected user role for the current session.
     */
    private UserRole selectedRole;

    /**
     * Creates a user session with public user role by default.
     */
    public UserSession() {
        this.selectedRole = UserRole.PUBLIC_USER;
    }

    /**
     * Updates the selected user role.
     *
     * @param role the user role to assign
     */
    public void setRole(UserRole role) {
        this.selectedRole = role;
    }

    /**
     * Returns the current selected user role.
     *
     * @return the current user role
     */
    public UserRole getRole() {
        return this.selectedRole;
    }
}