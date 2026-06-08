package drsinitial.session;

import drsinitial.model.enums.UserRole;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests prototype user session role storage behaviour
 * for the Disaster Response System prototype.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class UserSessionTest {

    /**
     * TP15
     *
     * Tests that a new user session starts with PUBLIC_USER
     * as the default selected role.
     */
    @Test
    public void testDefaultRoleIsPublicUser() {

        UserSession session = new UserSession();

        assertEquals(UserRole.EMERGENCY_CONTROL_CENTRE, UserSession.getCurrentRole());
    }

    /**
     * TP15
     *
     * Tests that the selected user role is stored and
     * returned correctly.
     */
    @Test
    public void testSetAndGetRole() {

        UserSession session = new UserSession();

        session.setRole(UserRole.EMERGENCY_CONTROL_CENTRE);

        assertEquals(UserRole.EMERGENCY_CONTROL_CENTRE,
                session.getRole());
    }

    /**
     * TP15
     *
     * Tests that the selected user role can be changed.
     */
    @Test
    public void testChangeRole() {

        UserSession session = new UserSession();

        session.setRole(UserRole.PUBLIC_USER);
        assertEquals(UserRole.PUBLIC_USER, session.getRole());

        session.setRole(UserRole.SYSTEM_ADMINISTRATOR);
        assertEquals(UserRole.SYSTEM_ADMINISTRATOR,
                session.getRole());
    }
}