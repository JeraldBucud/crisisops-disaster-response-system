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
 * Provides validation logic for evacuation shelter records.
 */
public class ShelterValidationService {

    public boolean isValidShelter(EvacuationShelter shelter) {
        if (shelter == null) {
            return false;
        }

        if (isBlank(shelter.getShelterId())
                || isBlank(shelter.getShelterName())
                || isBlank(shelter.getLocation())
                || isBlank(shelter.getShelterStatus())
                || isBlank(shelter.getLastUpdated())) {
            return false;
        }

        if (shelter.getTotalCapacity() <= 0) {
            return false;
        }

        if (shelter.getCurrentOccupants() < 0) {
            return false;
        }

        if (shelter.getCurrentOccupants() > shelter.getTotalCapacity()) {
            return false;
        }

        return true;
    }

    public boolean hasValidCapacity(int totalCapacity, int currentOccupants) {
        return totalCapacity > 0
                && currentOccupants >= 0
                && currentOccupants <= totalCapacity;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
