/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drsinitial.service;

import drsinitial.model.EvacuationShelter;
import drsinitial.service.ShelterValidationService;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cardoso Pepe
 */


public class ShelterValidationServiceTest {

    private final ShelterValidationService service = new ShelterValidationService();

    @Test
    public void testValidShelter() {
        EvacuationShelter shelter = new EvacuationShelter(
                "SH001", "City Hall Shelter", "Brisbane CBD",
                100, 60, "Available", "2026-06-08"
        );

        assertTrue(service.isValidShelter(shelter));
    }

    @Test
    public void testInvalidShelterCapacity() {
        EvacuationShelter shelter = new EvacuationShelter(
                "SH002", "School Shelter", "South Bank",
                50, 70, "Available", "2026-06-08"
        );

        assertFalse(service.isValidShelter(shelter));
    }
}