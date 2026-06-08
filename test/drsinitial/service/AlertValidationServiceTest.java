/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drsinitial.service;

import drsinitial.model.PublicAlert;
import drsinitial.service.AlertValidationService;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cardoso Pepe
 */


public class AlertValidationServiceTest {

    private final AlertValidationService service =
            new AlertValidationService();

    @Test
    public void testValidPublicAlert() {

        PublicAlert alert = new PublicAlert(
                "ALT001",
                "INC001",
                "Evacuation",
                "Brisbane CBD",
                "HIGH",
                "Evacuate immediately.",
                "Admin",
                "2026-06-08",
                "Draft"
        );

        assertTrue(service.isValidAlert(alert));
    }

    @Test
    public void testEmptyAlertMessage() {

        PublicAlert alert = new PublicAlert(
                "ALT002",
                "INC002",
                "Warning",
                "South Bank",
                "MEDIUM",
                "",
                "Admin",
                "2026-06-08",
                "Draft"
        );

        assertFalse(service.isValidAlert(alert));
    }
}