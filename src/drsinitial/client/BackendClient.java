package drsinitial.client;

import drsinitial.model.DisasterReport;
import drsinitial.model.EmergencyResponse;
import drsinitial.model.EvacuationShelter;
import drsinitial.model.IncidentUpdate;
import drsinitial.model.PublicAlert;
import drsinitial.model.User;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides frontend methods used by JavaFX controllers to communicate
 * with the DRS-Enhanced backend server.
 *
 * Controllers should call this class instead of calling ClientConnection
 * directly. This keeps the JavaFX frontend separated from the backend
 * communication layer.
 *
 * This class does not connect directly to MySQL.
 *
 * @author Jerald Christopher Bucud
 */
public class BackendClient {

    private final ClientConnection clientConnection;

    /**
     * Creates a backend client using the default server connection.
     */
    public BackendClient() {
        this.clientConnection = new ClientConnection();
    }

    /**
     * Creates a backend client using a provided client connection.
     *
     * @param clientConnection client connection
     */
    public BackendClient(ClientConnection clientConnection) {
        this.clientConnection = clientConnection;
    }

    /**
     * Sends a login request to the backend server.
     *
     * @param username username
     * @param password password
     * @return backend response
     */
    public ClientResponse login(String username, String password) {
        ClientRequest request = new ClientRequest(ClientRequest.LOGIN);
        request.addData("username", safe(username));
        request.addData("password", safe(password));

        return send(request);
    }

    /**
     * Sends a public user registration request to the backend server.
     *
     * @param user public user
     * @param password account password
     * @return backend response
     */
    public ClientResponse registerPublicUser(User user, String password) {
        ClientRequest request =
                new ClientRequest(ClientRequest.REGISTER_PUBLIC_USER);

        addUserData(request, user);
        request.addData("password", safe(password));

        return send(request);
    }

    /**
     * Requests all system users from the backend server.
     *
     * @return backend response
     */
    public ClientResponse getUsers() {
        ClientRequest request = new ClientRequest(ClientRequest.GET_USERS);
        return send(request);
    }

    /**
     * Sends an add user request to the backend server.
     *
     * @param user user to add
     * @return backend response
     */
    public ClientResponse addUser(User user) {
        ClientRequest request = new ClientRequest(ClientRequest.ADD_USER);
        addUserData(request, user);

        return send(request);
    }

    /**
     * Sends an update user request to the backend server.
     *
     * @param user user to update
     * @return backend response
     */
    public ClientResponse updateUser(User user) {
        ClientRequest request = new ClientRequest(ClientRequest.UPDATE_USER);
        addUserData(request, user);

        return send(request);
    }

    /**
     * Sends a disaster report submission request to the backend server.
     *
     * @param report disaster report
     * @return backend response
     */
    public ClientResponse submitDisasterReport(DisasterReport report) {
        ClientRequest request =
                new ClientRequest(ClientRequest.SUBMIT_DISASTER_REPORT);

        if (report != null) {
            request.addData("reportId", safe(report.getReportId()));
            request.addData("reporterName", safe(report.getReporterName()));
            request.addData("disasterType",
                    enumValue(report.getDisasterType()));
            request.addData("location", safe(report.getLocation()));
            request.addData("description", safe(report.getDescription()));
            request.addData("dateTime", objectValue(report.getDateTime()));
            request.addData("initialSeverity",
                    enumValue(report.getInitialSeverity()));
            request.addData("reportStatus",
                    enumValue(report.getReportStatus()));
        }

        return send(request);
    }

    /**
     * Requests disaster reports from the backend server.
     *
     * @return backend response
     */
    public ClientResponse getDisasterReports() {
        ClientRequest request =
                new ClientRequest(ClientRequest.GET_DISASTER_REPORTS);

        return send(request);
    }

    /**
     * Sends an incident registration request to the backend server.
     *
     * @param reportId report ID
     * @param affectedPeople affected people count
     * @param affectedArea affected area
     * @return backend response
     */
    public ClientResponse registerIncident(String reportId,
            int affectedPeople, String affectedArea) {
        ClientRequest request =
                new ClientRequest(ClientRequest.REGISTER_INCIDENT);

        request.addData("reportId", safe(reportId));
        request.addData("affectedPeople", String.valueOf(affectedPeople));
        request.addData("affectedArea", safe(affectedArea));

        return send(request);
    }

    /**
     * Sends an incident priority assessment request to the backend server.
     *
     * @param incidentId incident ID
     * @return backend response
     */
    public ClientResponse assessIncidentPriority(String incidentId) {
        ClientRequest request =
                new ClientRequest(ClientRequest.ASSESS_INCIDENT_PRIORITY);

        request.addData("incidentId", safe(incidentId));

        return send(request);
    }

    /**
     * Sends an incident status update request to the backend server.
     *
     * @param update incident update
     * @return backend response
     */
    public ClientResponse updateIncidentStatus(IncidentUpdate update) {
        ClientRequest request =
                new ClientRequest(ClientRequest.UPDATE_INCIDENT_STATUS);

        if (update != null) {
            request.addData("updateId", safe(update.getUpdateId()));
            request.addData("incidentId", safe(update.getIncidentId()));
            request.addData("updateNotes", safe(update.getUpdateNotes()));
            request.addData("updatedBy", safe(update.getUpdatedBy()));
            request.addData("updateDateTime",
                    objectValue(update.getUpdateDateTime()));
            request.addData("updatedStatus",
                    enumValue(update.getUpdatedStatus()));
        }

        return send(request);
    }

    /**
     * Sends an incident search request to the backend server.
     *
     * @param criteria search and filter criteria
     * @return backend response
     */
    public ClientResponse searchIncidents(Map<String, String> criteria) {
        ClientRequest request =
                new ClientRequest(ClientRequest.SEARCH_INCIDENTS);

        request.setData(copyMap(criteria));

        return send(request);
    }

    /**
     * Sends an emergency dispatch request to the backend server.
     *
     * @param response emergency response
     * @return backend response
     */
    public ClientResponse dispatchResponse(EmergencyResponse response) {
        ClientRequest request =
                new ClientRequest(ClientRequest.DISPATCH_RESPONSE);

        if (response != null) {
            request.addData("responseId", safe(response.getResponseId()));
            request.addData("incidentId",
                    response.getIncident() == null
                            ? ""
                            : safe(response.getIncident().getIncidentId()));
            request.addData("agencyId",
                    response.getResponseAgency() == null
                            ? ""
                            : safe(response.getResponseAgency().getAgencyId()));
            request.addData("resourceId",
                    response.getEmergencyResource() == null
                            ? ""
                            : safe(response.getEmergencyResource()
                                    .getResourceId()));
            request.addData("dispatchNotes",
                    safe(response.getDispatchNotes()));
            request.addData("dispatchDateTime",
                    objectValue(response.getDispatchDateTime()));
            request.addData("responseStatus",
                    enumValue(response.getResponseStatus()));
        }

        return send(request);
    }

    /**
     * Requests emergency response logs from the backend server.
     *
     * @return backend response
     */
    public ClientResponse getResponseLogs() {
        ClientRequest request =
                new ClientRequest(ClientRequest.GET_RESPONSE_LOGS);

        return send(request);
    }

    /**
     * Requests emergency resources from the backend server.
     *
     * @return backend response
     */
    public ClientResponse getEmergencyResources() {
        ClientRequest request =
                new ClientRequest(ClientRequest.GET_EMERGENCY_RESOURCES);

        return send(request);
    }

    /**
     * Requests response agencies from the backend server.
     *
     * @return backend response
     */
    public ClientResponse getResponseAgencies() {
        ClientRequest request =
                new ClientRequest(ClientRequest.GET_RESPONSE_AGENCIES);

        return send(request);
    }

    /**
     * Sends a resource availability update request to the backend server.
     *
     * @param resourceId resource ID
     * @param action action name
     * @return backend response
     */
    public ClientResponse updateResourceAvailability(String resourceId,
            String action) {
        ClientRequest request =
                new ClientRequest(ClientRequest.UPDATE_RESOURCE_AVAILABILITY);

        request.addData("resourceId", safe(resourceId));
        request.addData("action", safe(action));

        return send(request);
    }

    /**
     * Requests evacuation shelters from the backend server.
     *
     * @return backend response
     */
    public ClientResponse getEvacuationShelters() {
        ClientRequest request =
                new ClientRequest(ClientRequest.GET_EVACUATION_SHELTERS);

        return send(request);
    }

    /**
     * Sends an add evacuation shelter request to the backend server.
     *
     * @param shelter evacuation shelter
     * @return backend response
     */
    public ClientResponse addEvacuationShelter(EvacuationShelter shelter) {
        ClientRequest request =
                new ClientRequest(ClientRequest.ADD_EVACUATION_SHELTER);

        addShelterData(request, shelter);

        return send(request);
    }

    /**
     * Sends an update evacuation shelter request to the backend server.
     *
     * @param shelter evacuation shelter
     * @return backend response
     */
    public ClientResponse updateEvacuationShelter(EvacuationShelter shelter) {
        ClientRequest request =
                new ClientRequest(ClientRequest.UPDATE_EVACUATION_SHELTER);

        addShelterData(request, shelter);

        return send(request);
    }

    /**
     * Requests active published public alerts for Public User.
     *
     * @return backend response
     */
    public ClientResponse getPublicAlertsForPublicUser() {
        ClientRequest request =
                new ClientRequest(
                        ClientRequest.GET_PUBLIC_ALERTS_FOR_PUBLIC_USER);

        return send(request);
    }

    /**
     * Requests all public alerts for ECC and Admin.
     *
     * @return backend response
     */
    public ClientResponse getAllPublicAlerts() {
        ClientRequest request =
                new ClientRequest(ClientRequest.GET_ALL_PUBLIC_ALERTS);

        return send(request);
    }

    /**
     * Sends a create public alert request to the backend server.
     *
     * @param alert public alert
     * @return backend response
     */
    public ClientResponse createPublicAlert(PublicAlert alert) {
        ClientRequest request =
                new ClientRequest(ClientRequest.CREATE_PUBLIC_ALERT);

        addPublicAlertData(request, alert);

        return send(request);
    }

    /**
     * Sends a publish public alert request to the backend server.
     *
     * @param alertId alert ID
     * @return backend response
     */
    public ClientResponse publishPublicAlert(String alertId) {
        ClientRequest request =
                new ClientRequest(ClientRequest.PUBLISH_PUBLIC_ALERT);

        request.addData("alertId", safe(alertId));

        return send(request);
    }

    /**
     * Sends an expire public alert request to the backend server.
     *
     * @param alertId alert ID
     * @return backend response
     */
    public ClientResponse expirePublicAlert(String alertId) {
        ClientRequest request =
                new ClientRequest(ClientRequest.EXPIRE_PUBLIC_ALERT);

        request.addData("alertId", safe(alertId));

        return send(request);
    }

    /**
     * Sends a request through the client connection.
     *
     * @param request client request
     * @return client response
     */
    private ClientResponse send(ClientRequest request) {
        return this.clientConnection.sendRequest(request);
    }

    /**
     * Adds user values to a client request.
     *
     * @param request client request
     * @param user user
     */
    private void addUserData(ClientRequest request, User user) {
        if (user == null) {
            return;
        }

        request.addData("userId", safe(user.getUserId()));
        request.addData("fullName", safe(user.getFullName()));
        request.addData("email", safe(user.getEmail()));
        request.addData("username", safe(user.getUsername()));
        request.addData("role", safe(user.getRole()));
        request.addData("accountStatus", safe(user.getAccountStatus()));
    }

    /**
     * Adds shelter values to a client request.
     *
     * @param request client request
     * @param shelter evacuation shelter
     */
    private void addShelterData(ClientRequest request,
            EvacuationShelter shelter) {
        if (shelter == null) {
            return;
        }

        request.addData("shelterId", safe(shelter.getShelterId()));
        request.addData("shelterName", safe(shelter.getShelterName()));
        request.addData("location", safe(shelter.getLocation()));
        request.addData("totalCapacity",
                String.valueOf(shelter.getTotalCapacity()));
        request.addData("currentOccupants",
                String.valueOf(shelter.getCurrentOccupants()));
        request.addData("availableSpaces",
                String.valueOf(shelter.getAvailableSpaces()));
        request.addData("shelterStatus", safe(shelter.getShelterStatus()));
        request.addData("lastUpdated", safe(shelter.getLastUpdated()));
    }

    /**
     * Adds public alert values to a client request.
     *
     * @param request client request
     * @param alert public alert
     */
    private void addPublicAlertData(ClientRequest request,
            PublicAlert alert) {
        if (alert == null) {
            return;
        }

        request.addData("alertId", safe(alert.getAlertId()));
        request.addData("incidentId", safe(alert.getIncidentId()));
        request.addData("alertType", safe(alert.getAlertType()));
        request.addData("affectedArea", safe(alert.getAffectedArea()));
        request.addData("severityLevel", safe(alert.getSeverityLevel()));
        request.addData("alertMessage", safe(alert.getAlertMessage()));
        request.addData("createdBy", safe(alert.getCreatedBy()));
        request.addData("createdTime", safe(alert.getCreatedTime()));
        request.addData("alertStatus", safe(alert.getAlertStatus()));
    }

    /**
     * Creates a safe copy of a map.
     *
     * @param source source map
     * @return copied map
     */
    private Map<String, String> copyMap(Map<String, String> source) {
        Map<String, String> copy = new HashMap<>();

        if (source == null) {
            return copy;
        }

        copy.putAll(source);
        return copy;
    }

    /**
     * Returns an empty string if the value is null.
     *
     * @param value string value
     * @return safe string value
     */
    private String safe(String value) {
        if (value == null) {
            return "";
        }

        return value;
    }

    /**
     * Converts an enum value to a safe string.
     *
     * @param value enum value
     * @return enum name or empty string
     */
    private String enumValue(Enum<?> value) {
        if (value == null) {
            return "";
        }

        return value.name();
    }

    /**
     * Converts an object value to a safe string.
     *
     * @param value object value
     * @return string value or empty string
     */
    private String objectValue(Object value) {
        if (value == null) {
            return "";
        }

        return value.toString();
    }
}