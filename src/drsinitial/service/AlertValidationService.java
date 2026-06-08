/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drsinitial.service;

import drsinitial.model.PublicAlert;

/**
 *
 * @author Cardoso Pepe
 */


public class AlertValidationService {

    public boolean isValidAlert(PublicAlert alert) {
        if (alert == null) {
            return false;
        }

        return !isBlank(alert.getAlertId())
                && !isBlank(alert.getIncidentId())
                && !isBlank(alert.getAlertType())
                && !isBlank(alert.getAffectedArea())
                && !isBlank(alert.getSeverityLevel())
                && !isBlank(alert.getAlertMessage())
                && !isBlank(alert.getCreatedBy())
                && !isBlank(alert.getCreatedTime())
                && isValidAlertStatus(alert.getAlertStatus());
    }

    public boolean isValidAlertStatus(String status) {
        if (isBlank(status)) {
            return false;
        }

        return status.equalsIgnoreCase("Draft")
                || status.equalsIgnoreCase("Published")
                || status.equalsIgnoreCase("Cancelled")
                || status.equalsIgnoreCase("Expired");
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
