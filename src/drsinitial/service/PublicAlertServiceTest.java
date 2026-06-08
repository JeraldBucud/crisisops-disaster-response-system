/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drsinitial.service;

import drsinitial.model.PublicAlert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cardoso Pepe
 */

public class PublicAlertServiceTest {

    private final PublicAlertService service =
            new PublicAlertService();

    @Test
    public void testPublishAlert() {

        PublicAlert alert = new PublicAlert(
                "ALT003",
                "INC003",
                "Emergency",
                "Toowong",
                "HIGH",
                "Flood warning.",
                "Admin",
                "2026-06-08",
                "Draft"
        );

        service.publishAlert(alert);

        assertEquals("Published",
                alert.getAlertStatus());
    }

    @Test
    public void testCancelAlert() {

        PublicAlert alert = new PublicAlert(
                "ALT004",
                "INC004",
                "Emergency",
                "West End",
                "HIGH",
                "Fire warning.",
                "Admin",
                "2026-06-08",
                "Draft"
        );

        service.cancelAlert(alert);

        assertEquals("Cancelled",
                alert.getAlertStatus());
    }
}
