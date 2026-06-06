package drsinitial.model;

/**
 * Represents a system user account for DRS-Enhanced.
 *
 * This model supports role-based access control and
 * administrator user management.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class User {

    private String userId;
    private String fullName;
    private String email;
    private String username;
    private String role;
    private String accountStatus;

    /**
     * Creates a user record.
     *
     * @param userId user ID
     * @param fullName full name
     * @param email email address
     * @param username username
     * @param role user role
     * @param accountStatus account status
     */
    public User(String userId,
            String fullName,
            String email,
            String username,
            String role,
            String accountStatus) {

        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.role = role;
        this.accountStatus = accountStatus;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccountStatus() {
        return this.accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return this.userId + " - " + this.username + " (" + this.role + ")";
    }
}