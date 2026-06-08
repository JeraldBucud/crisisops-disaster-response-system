/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drsinitial.service;

import drsinitial.model.EvacuationShelter;
import drsinitial.service.ShelterAvailabilityService;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cardoso Pepe
 */

public class ShelterAvailabilityServiceTest {

    private final ShelterAvailabilityService service = new ShelterAvailabilityService();

    @Test
    public void testCalculateAvailableSpaces() {
        EvacuationShelter shelter = new EvacuationShelter(
                "SH003", "Community Centre", "Fortitude Valley",
                120, 45, "Available", "2026-06-08"
        );

        assertEquals(75, service.calculateAvailableSpaces(shelter));
    }

    @Test
    public void testNearCapacityStatus() {
        EvacuationShelter shelter = new EvacuationShelter(
                "SH004", "Sports Hall", "New Farm",
                100, 95, "Available", "2026-06-08"
        );

        assertEquals("Near Capacity", service.determineShelterStatus(shelter));
    }

    @Test
    public void testFullStatus() {
        EvacuationShelter shelter = new EvacuationShelter(
                "SH005", "Library Shelter", "Toowong",
                100, 100, "Available", "2026-06-08"
        );

        assertEquals("Full", service.determineShelterStatus(shelter));
    }
}
