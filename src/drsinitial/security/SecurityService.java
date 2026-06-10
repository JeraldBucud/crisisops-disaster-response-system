package drsinitial.security;

/**
 * Provides role-based access checks for DRS-Enhanced server requests.
 */
public class SecurityService {

    public boolean canManageUsers(String role) {
        return "SYSTEM_ADMINISTRATOR".equals(role);
    }

    public boolean canManageOperationalData(String role) {
        return "SYSTEM_ADMINISTRATOR".equals(role)
                || "EMERGENCY_CONTROL_CENTRE".equals(role);
    }

    public boolean canSubmitPublicReport(String role) {
        return "PUBLIC_USER".equals(role)
                || "EMERGENCY_CONTROL_CENTRE".equals(role)
                || "SYSTEM_ADMINISTRATOR".equals(role);
    }
}
