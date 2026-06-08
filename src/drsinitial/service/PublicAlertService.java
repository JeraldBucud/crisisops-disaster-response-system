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


public class PublicAlertService {

    private final AlertValidationService validationService =
            new AlertValidationService();

    public boolean canCreateAlert(PublicAlert alert) {
        return validationService.isValidAlert(alert);
    }

    public void publishAlert(PublicAlert alert) {
        if (alert != null && validationService.isValidAlert(alert)) {
            alert.setAlertStatus("Published");
        }
    }

    public void cancelAlert(PublicAlert alert) {
        if (alert != null) {
            alert.setAlertStatus("Cancelled");
        }
    }

    public void expireAlert(PublicAlert alert) {
        if (alert != null) {
            alert.setAlertStatus("Expired");
        }
    }
}
