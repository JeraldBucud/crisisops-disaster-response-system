/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drsinitial.service;

import drsinitial.model.EvacuationShelter;

/**
 *
 * @author Cardoso Pepe
 */


/**
 * Provides business logic for shelter availability.
 */
public class ShelterAvailabilityService {

    public int calculateAvailableSpaces(EvacuationShelter shelter) {
        if (shelter == null) {
            return 0;
        }

        int availableSpaces = shelter.getTotalCapacity()
                - shelter.getCurrentOccupants();

        return Math.max(availableSpaces, 0);
    }

    public String determineShelterStatus(EvacuationShelter shelter) {
        if (shelter == null) {
            return "Closed";
        }

        if ("Closed".equalsIgnoreCase(shelter.getShelterStatus())) {
            return "Closed";
        }

        int totalCapacity = shelter.getTotalCapacity();
        int currentOccupants = shelter.getCurrentOccupants();

        if (totalCapacity <= 0 || currentOccupants >= totalCapacity) {
            return "Full";
        }

        double occupancyRate = (double) currentOccupants / totalCapacity;

        if (occupancyRate >= 0.90) {
            return "Near Capacity";
        }

        return "Available";
    }

    public void updateShelterAvailability(EvacuationShelter shelter) {
        if (shelter == null) {
            return;
        }

        shelter.setAvailableSpaces(calculateAvailableSpaces(shelter));
        shelter.setShelterStatus(determineShelterStatus(shelter));
    }
}
