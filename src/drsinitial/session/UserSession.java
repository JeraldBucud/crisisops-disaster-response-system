package drsinitial.session;

import drsinitial.model.enums.UserRole;

/**
 * Stores the selected user role and login details during the
 * running session.
 *
 * This class supports role-based access control in
 * DRS-Enhanced.
 *
 * @author Jerald Christopher Bucud
 */
public class UserSession {

    private static String currentUsername = "public";
    private static String currentDisplayName = "Public User";
    private static UserRole currentRole = UserRole.PUBLIC_USER;

    /**
     * Stores the selected user role for object-based usage.
     */
    private UserRole selectedRole;

    /**
     * Creates a user session with public user role by default.
     */
    public UserSession() {
        this.selectedRole = currentRole;
    }

    /**
     * Stores successful login details.
     *
     * @param username logged-in username
     * @param displayName user display name
     * @param role user role
     */
    public static void login(String username,
            String displayName,
            UserRole role) {

        currentUsername = username;
        currentDisplayName = displayName;
        currentRole = role;
    }

    /**
     * Clears the active session.
     */
    public static void logout() {
        currentUsername = "public";
        currentDisplayName = "Public User";
        currentRole = UserRole.PUBLIC_USER;
    }

    /**
     * Returns the current username.
     *
     * @return current username
     */
    public static String getCurrentUsername() {
        return currentUsername;
    }

    /**
     * Returns the current display name.
     *
     * @return current display name
     */
    public static String getCurrentDisplayName() {
        return currentDisplayName;
    }

    /**
     * Returns the current logged-in role.
     *
     * @return current user role
     */
    public static UserRole getCurrentRole() {
        return currentRole;
    }

    /**
     * Returns a readable version of the current role.
     *
     * @return readable role name
     */
    public static String getCurrentRoleDisplayName() {
        if (currentRole == UserRole.SYSTEM_ADMINISTRATOR) {
            return "System Administrator";
        }

        if (currentRole == UserRole.EMERGENCY_CONTROL_CENTRE) {
            return "Emergency Control Centre";
        }

        return "Public User";
    }

    /**
     * Updates the selected user role.
     *
     * @param role the user role to assign
     */
    public void setRole(UserRole role) {
        this.selectedRole = role;
        currentRole = role;
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