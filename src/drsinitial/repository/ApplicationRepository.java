package drsinitial.repository;

import drsinitial.model.DisasterReport;
import drsinitial.model.EmergencyResource;
import drsinitial.model.EmergencyResponse;
import drsinitial.model.Incident;
import drsinitial.model.IncidentUpdate;
import drsinitial.model.ResponseAgency;
import drsinitial.model.enums.AgencyType;
import drsinitial.model.enums.DisasterType;
import drsinitial.model.enums.IncidentStatus;
import drsinitial.model.enums.PriorityLevel;
import drsinitial.model.enums.ResourceStatus;
import drsinitial.model.enums.SeverityLevel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import drsinitial.model.EvacuationShelter;
import drsinitial.model.PublicAlert;
import drsinitial.model.User;

/**
 * Provides central in-memory storage for the DRS-Initial prototype.
 *
 * This repository stores disaster reports, incidents, incident updates,
 * emergency responses, emergency resources, and response agencies.
 * It also provides helper methods for adding records, generating
 * identifiers, searching records, and loading sample data.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class ApplicationRepository {

    /**
     * Stores all disaster reports submitted to the system.
     */
    private static final ObservableList<DisasterReport> disasterReports =
            FXCollections.observableArrayList();

    /**
     * Stores all registered disaster incidents.
     */
    private static final ObservableList<Incident> incidents =
            FXCollections.observableArrayList();

    /**
     * Stores all updates recorded for incidents.
     */
    private static final ObservableList<IncidentUpdate> incidentUpdates =
            FXCollections.observableArrayList();

    /**
     * Stores all emergency responses dispatched for incidents.
     */
    private static final ObservableList<EmergencyResponse> emergencyResponses =
            FXCollections.observableArrayList();

    /**
     * Stores all emergency resources available to the system.
     */
    private static final ObservableList<EmergencyResource> emergencyResources =
            FXCollections.observableArrayList();

    /**
     * Stores all response agencies registered in the system.
     */
    private static final ObservableList<ResponseAgency> responseAgencies =
            FXCollections.observableArrayList();

    /**
     * Tracks whether sample data has already been loaded.
     */
    private static boolean sampleDataLoaded = false;

    /**
     * Prevents object creation for this utility repository class.
     */
    private ApplicationRepository() {

    }

    /**
     * Returns the disaster report list.
     *
     * @return observable list of disaster reports
     */
    public static ObservableList<DisasterReport> getDisasterReports() {
        return disasterReports;
    }

    /**
     * Returns the incident list.
     *
     * @return observable list of incidents
     */
    public static ObservableList<Incident> getIncidents() {
        return incidents;
    }

    /**
     * Returns the incident update list.
     *
     * @return observable list of incident updates
     */
    public static ObservableList<IncidentUpdate> getIncidentUpdates() {
        return incidentUpdates;
    }

    /**
     * Returns the emergency response list.
     *
     * @return observable list of emergency responses
     */
    public static ObservableList<EmergencyResponse> getEmergencyResponses() {
        return emergencyResponses;
    }

    /**
     * Returns the emergency resource list.
     *
     * @return observable list of emergency resources
     */
    public static ObservableList<EmergencyResource> getEmergencyResources() {
        return emergencyResources;
    }

    /**
     * Returns the response agency list.
     *
     * @return observable list of response agencies
     */
    public static ObservableList<ResponseAgency> getResponseAgencies() {
        return responseAgencies;
    }

    /**
     * Adds a disaster report to the repository.
     *
     * @param report disaster report to add
     */
    public static void addDisasterReport(DisasterReport report) {
        disasterReports.add(report);
    }

    /**
     * Adds an incident to the repository.
     *
     * @param incident incident to add
     */
    public static void addIncident(Incident incident) {
        incidents.add(incident);
    }

    /**
     * Adds an incident update to the repository.
     *
     * @param update incident update to add
     */
    public static void addIncidentUpdate(IncidentUpdate update) {
        incidentUpdates.add(update);
    }

    /**
     * Adds an emergency response to the repository.
     *
     * @param response emergency response to add
     */
    public static void addEmergencyResponse(EmergencyResponse response) {
        emergencyResponses.add(response);
    }

    /**
     * Adds an emergency resource to the repository.
     *
     * @param resource emergency resource to add
     */
    public static void addEmergencyResource(EmergencyResource resource) {
        emergencyResources.add(resource);
    }

    /**
     * Adds a response agency to the repository.
     *
     * @param agency response agency to add
     */
    public static void addResponseAgency(ResponseAgency agency) {
        responseAgencies.add(agency);
    }
    
    private static final ObservableList<EvacuationShelter> evacuationShelters =
        FXCollections.observableArrayList();

    private static final ObservableList<PublicAlert> publicAlerts =
            FXCollections.observableArrayList();

    private static final ObservableList<User> systemUsers =
            FXCollections.observableArrayList();
    
    public static ObservableList<EvacuationShelter> getEvacuationShelters() {
    return evacuationShelters;
    }

    public static ObservableList<PublicAlert> getPublicAlerts() {
        return publicAlerts;
    }

    public static ObservableList<User> getSystemUsers() {
        return systemUsers;
    }

    public static void addEvacuationShelter(EvacuationShelter shelter) {
        evacuationShelters.add(shelter);
    }

    public static void addPublicAlert(PublicAlert alert) {
        publicAlerts.add(alert);
    }

    public static void addSystemUser(User user) {
        systemUsers.add(user);
    }

    public static String generateShelterId() {
        return String.format("SH%03d", evacuationShelters.size() + 1);
    }

    public static String generateAlertId() {
        return String.format("AL%03d", publicAlerts.size() + 1);
    }

    public static String generateUserId() {
        return String.format("U%03d", systemUsers.size() + 1);
    }


    /**
     * Generates the next disaster report identifier.
     *
     * @return generated report identifier
     */
    public static String generateReportId() {
        return String.format("R%03d", disasterReports.size() + 1);
    }

    /**
     * Generates the next incident identifier.
     *
     * @return generated incident identifier
     */
    public static String generateIncidentId() {
        return String.format("I%03d", incidents.size() + 1);
    }

    /**
     * Generates the next emergency response identifier.
     *
     * @return generated emergency response identifier
     */
    public static String generateResponseId() {
        return String.format("ER%03d", emergencyResponses.size() + 1);
    }

    /**
     * Generates the next incident update identifier.
     *
     * @return generated incident update identifier
     */
    public static String generateUpdateId() {
        return String.format("U%03d", incidentUpdates.size() + 1);
    }

    /**
     * Finds a disaster report using its report identifier.
     *
     * @param reportId report identifier to search for
     * @return matching disaster report if found, otherwise null
     */
    public static DisasterReport findReportById(String reportId) {

        for (DisasterReport report : disasterReports) {
            if (report.getReportId().equalsIgnoreCase(reportId)) {
                return report;
            }
        }

        return null;
    }

    /**
     * Finds an incident using its incident identifier.
     *
     * @param incidentId incident identifier to search for
     * @return matching incident if found, otherwise null
     */
    public static Incident findIncidentById(String incidentId) {

        for (Incident incident : incidents) {
            if (incident.getIncidentId().equalsIgnoreCase(incidentId)) {
                return incident;
            }
        }

        return null;
    }

    /**
     * Loads sample data into the repository.
     *
     * This method creates sample disaster reports, incidents,
     * emergency resources, response agencies, incident updates,
     * and emergency responses for prototype demonstration and testing.
     * Sample data is loaded once only.
     */
    public static void loadSampleData() {

        if (sampleDataLoaded) {
            return;
        }

        DisasterReport reportOne = new DisasterReport(
                "R001",
                "Juan Dela Cruz",
                DisasterType.FIRE,
                "Brisbane CBD",
                "Building fire reported near the main road. "
                        + "Smoke is visible from the second floor.",
                SeverityLevel.HIGH
        );

        DisasterReport reportTwo = new DisasterReport(
                "R002",
                "Maria Santos",
                DisasterType.FLOOD,
                "South Bank",
                "Flood water is rising near residential streets. "
                        + "Several families need evacuation support.",
                SeverityLevel.CRITICAL
        );

        DisasterReport reportThree = new DisasterReport(
                "R003",
                "Peter Wilson",
                DisasterType.STORM,
                "Fortitude Valley",
                "Severe storm damage has affected power lines "
                        + "and blocked one side road.",
                SeverityLevel.MEDIUM
        );

        disasterReports.addAll(reportOne, reportTwo, reportThree);

        Incident incidentOne = new Incident(
                "I001",
                "R001",
                15,
                "Brisbane CBD"
        );
        incidentOne.assessSeverity(SeverityLevel.HIGH);
        incidentOne.updatePriority(PriorityLevel.HIGH);

        Incident incidentTwo = new Incident(
                "I002",
                "R002",
                40,
                "South Bank"
        );
        incidentTwo.assessSeverity(SeverityLevel.CRITICAL);
        incidentTwo.updatePriority(PriorityLevel.EMERGENCY);

        Incident incidentThree = new Incident(
                "I003",
                "R003",
                8,
                "Fortitude Valley"
        );
        incidentThree.assessSeverity(SeverityLevel.MEDIUM);
        incidentThree.updatePriority(PriorityLevel.MEDIUM);

        incidents.addAll(incidentOne, incidentTwo, incidentThree);

        EmergencyResource ambulance = new EmergencyResource(
                "RES001",
                "Ambulance Unit",
                "Medical",
                4
        );

        EmergencyResource fireTruck = new EmergencyResource(
                "RES002",
                "Fire Truck",
                "Fire Response",
                3
        );

        EmergencyResource rescueTeam = new EmergencyResource(
                "RES003",
                "Rescue Team",
                "Search and Rescue",
                5
        );

        EmergencyResource policeUnit = new EmergencyResource(
                "RES004",
                "Police Unit",
                "Security",
                2
        );

        policeUnit.setResourceStatus(ResourceStatus.ASSIGNED);

        emergencyResources.addAll(
                ambulance,
                fireTruck,
                rescueTeam,
                policeUnit
        );

        ResponseAgency fireAgency = new ResponseAgency(
                "A001",
                "Fire and Emergency Services",
                "000",
                AgencyType.FIRE_RESPONSE
        );

        ResponseAgency hospitalAgency = new ResponseAgency(
                "A002",
                "Hospital Emergency Unit",
                "000",
                AgencyType.MEDICAL_RESPONSE
        );

        ResponseAgency lawAgency = new ResponseAgency(
                "A003",
                "Law Enforcement",
                "000",
                AgencyType.SECURITY_RESPONSE
        );

        responseAgencies.addAll(
                fireAgency,
                hospitalAgency,
                lawAgency
        );

        IncidentUpdate updateOne = new IncidentUpdate(
                "U001",
                "I001",
                "Fire response team assigned.",
                "Emergency Control Centre",
                IncidentStatus.DISPATCHED
        );

        IncidentUpdate updateTwo = new IncidentUpdate(
                "U002",
                "I002",
                "Flood area marked as urgent priority.",
                "Emergency Control Centre",
                IncidentStatus.PRIORITISED
        );

        incidentUpdates.addAll(updateOne, updateTwo);

        EmergencyResponse responseOne = new EmergencyResponse(
                "ER001",
                incidentOne,
                fireAgency,
                fireTruck,
                "Fire response dispatched to Brisbane CBD."
        );

        EmergencyResponse responseTwo = new EmergencyResponse(
                "ER002",
                incidentTwo,
                hospitalAgency,
                ambulance,
                "Medical support dispatched to South Bank."
        );

        emergencyResponses.addAll(responseOne, responseTwo);

        sampleDataLoaded = true;
    }
}