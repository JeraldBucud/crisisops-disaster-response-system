/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drsinitial.service;

import drsinitial.model.User;
import drsinitial.model.enums.UserRole;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cardoso Pepe
 */


public class UserRegistrationServiceTest {

    private final UserRegistrationService service =
            new UserRegistrationService();

    @Test
    public void testValidPublicUserRegistration() {
        User user = service.createPublicUser(
                "USR001",
                "Leo Student",
                "leo@student.com",
                "leostudent",
                "password123",
                "password123"
        );

        assertNotNull(user);
        assertEquals(UserRole.PUBLIC_USER.name(), user.getRole());
        assertEquals("Active", user.getAccountStatus());
    }

    @Test
    public void testInvalidEmailRegistration() {
        boolean result = service.isValidRegistration(
                "Leo Student",
                "invalid-email",
                "leostudent",
                "password123",
                "password123"
        );

        assertFalse(result);
    }

    @Test
    public void testPasswordMismatchRegistration() {
        boolean result = service.isValidRegistration(
                "Leo Student",
                "leo@student.com",
                "leostudent",
                "password123",
                "different123"
        );

        assertFalse(result);
    }
}
