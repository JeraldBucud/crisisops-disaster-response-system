package drsinitial.repository;

import drsinitial.model.DisasterReport;
import drsinitial.model.EmergencyResource;
import drsinitial.model.EmergencyResponse;
import drsinitial.model.EvacuationShelter;
import drsinitial.model.Incident;
import drsinitial.model.IncidentUpdate;
import drsinitial.model.PublicAlert;
import drsinitial.model.ResponseAgency;
import drsinitial.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Provides frontend in-memory cache lists for JavaFX table and combo box
 * binding.
 *
 * This class does not act as the final database layer.
 * Persistent storage is handled by the backend server and MySQL database.
 *
 * Backend data is loaded into these ObservableList objects so JavaFX
 * TableView and ComboBox controls can display current backend records.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class ApplicationRepository {

    private static final ObservableList<DisasterReport> disasterReports =
            FXCollections.observableArrayList();

    private static final ObservableList<Incident> incidents =
            FXCollections.observableArrayList();

    private static final ObservableList<IncidentUpdate> incidentUpdates =
            FXCollections.observableArrayList();

    private static final ObservableList<EmergencyResponse> emergencyResponses =
            FXCollections.observableArrayList();

    private static final ObservableList<EmergencyResource> emergencyResources =
            FXCollections.observableArrayList();

    private static final ObservableList<ResponseAgency> responseAgencies =
            FXCollections.observableArrayList();

    private static final ObservableList<EvacuationShelter> evacuationShelters =
            FXCollections.observableArrayList();

    private static final ObservableList<PublicAlert> publicAlerts =
            FXCollections.observableArrayList();

    private static final ObservableList<User> systemUsers =
            FXCollections.observableArrayList();

    private ApplicationRepository() {

    }

    public static ObservableList<DisasterReport> getDisasterReports() {
        return disasterReports;
    }

    public static ObservableList<Incident> getIncidents() {
        return incidents;
    }

    public static ObservableList<IncidentUpdate> getIncidentUpdates() {
        return incidentUpdates;
    }

    public static ObservableList<EmergencyResponse> getEmergencyResponses() {
        return emergencyResponses;
    }

    public static ObservableList<EmergencyResource> getEmergencyResources() {
        return emergencyResources;
    }

    public static ObservableList<ResponseAgency> getResponseAgencies() {
        return responseAgencies;
    }

    public static ObservableList<EvacuationShelter> getEvacuationShelters() {
        return evacuationShelters;
    }

    public static ObservableList<PublicAlert> getPublicAlerts() {
        return publicAlerts;
    }

    public static ObservableList<User> getSystemUsers() {
        return systemUsers;
    }

    /**
     * Finds a disaster report from the frontend cache.
     *
     * @param reportId report identifier
     * @return matching disaster report, or null if not found
     */
    public static DisasterReport findReportById(String reportId) {
        if (reportId == null) {
            return null;
        }

        for (DisasterReport report : disasterReports) {
            if (report.getReportId().equalsIgnoreCase(reportId)) {
                return report;
            }
        }

        return null;
    }

    /**
     * Finds an incident from the frontend cache.
     *
     * @param incidentId incident identifier
     * @return matching incident, or null if not found
     */
    public static Incident findIncidentById(String incidentId) {
        if (incidentId == null) {
            return null;
        }

        for (Incident incident : incidents) {
            if (incident.getIncidentId().equalsIgnoreCase(incidentId)) {
                return incident;
            }
        }

        return null;
    }

    /**
     * Clears all frontend cache lists.
     */
    public static void clearAll() {
        disasterReports.clear();
        incidents.clear();
        incidentUpdates.clear();
        emergencyResponses.clear();
        emergencyResources.clear();
        responseAgencies.clear();
        evacuationShelters.clear();
        publicAlerts.clear();
        systemUsers.clear();
    }
}