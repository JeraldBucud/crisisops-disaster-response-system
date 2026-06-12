package drsinitial.server;

import drsinitial.client.ClientRequest;
import drsinitial.client.ClientResponse;
import drsinitial.dao.AuditLogDAO;
import drsinitial.dao.DisasterReportDAO;
import drsinitial.dao.DispatchDAO;
import drsinitial.dao.EmergencyResourceDAO;
import drsinitial.dao.EvacuationShelterDAO;
import drsinitial.dao.IncidentDAO;
import drsinitial.dao.PublicAlertDAO;
import drsinitial.dao.UserDAO;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles one JavaFX client connection in a separate thread.
 */
public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final UserDAO userDAO;
    private final DisasterReportDAO disasterReportDAO;
    private final PublicAlertDAO publicAlertDAO;
    private final EvacuationShelterDAO shelterDAO;
    private final IncidentDAO incidentDAO;
    private final EmergencyResourceDAO resourceDAO;
    private final DispatchDAO dispatchDAO;
    private final AuditLogDAO auditLogDAO;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.userDAO = new UserDAO();
        this.disasterReportDAO = new DisasterReportDAO();
        this.publicAlertDAO = new PublicAlertDAO();
        this.shelterDAO = new EvacuationShelterDAO();
        this.incidentDAO = new IncidentDAO();
        this.resourceDAO = new EmergencyResourceDAO();
        this.dispatchDAO = new DispatchDAO();
        this.auditLogDAO = new AuditLogDAO();
    }

    @Override
    public void run() {
        try (
                ObjectOutputStream output
                = new ObjectOutputStream(clientSocket.getOutputStream()); ObjectInputStream input
                = new ObjectInputStream(clientSocket.getInputStream())) {
            Object object = input.readObject();
            ClientResponse response;

            if (object instanceof ClientRequest) {
                ClientRequest request = (ClientRequest) object;
                System.out.println("Request received: " + request.getRequestType());

                response = handleRequest(request);

                if (response.isSuccess()) {
                    System.out.println("Response sent: "
                            + request.getRequestType() + "_SUCCESS");
                } else {
                    System.out.println("Response sent: "
                            + request.getRequestType() + "_FAILED - "
                            + response.getMessage());
                }

            } else {
                response = ClientResponse.failure(
                        "Invalid object received by server.");
                System.out.println("Response sent: INVALID_OBJECT_FAILED");
            }

            output.writeObject(response);
            output.flush();

        } catch (Exception exception) {
            System.err.println("Client handler error: "
                    + exception.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (Exception ignored) {
            }
        }
    }

    private ClientResponse handleRequest(ClientRequest request) {
        try {
            String type = request.getRequestType();
            System.out.println("Request received: " + type);

            if (ClientRequest.LOGIN.equals(type)) {
                return handleLogin(request.getData());
            }

            if (ClientRequest.REGISTER_PUBLIC_USER.equals(type)) {
                return handleRegisterPublicUser(request.getData());
            }

            if (ClientRequest.GET_USERS.equals(type)) {
                return new ClientResponse(
                        true,
                        "Users loaded.",
                        userDAO.getAllUsers());
            }

            if (ClientRequest.ADD_USER.equals(type)) {
                return handleAddUser(request.getData());
            }

            if (ClientRequest.UPDATE_USER.equals(type)) {
                return handleUpdateUser(request.getData());
            }

            if (ClientRequest.SUBMIT_DISASTER_REPORT.equals(type)) {
                return handleSubmitDisasterReport(request.getData());
            }

            if (ClientRequest.GET_DISASTER_REPORTS.equals(type)) {
                return new ClientResponse(
                        true,
                        "Disaster reports loaded.",
                        disasterReportDAO.getAllReports());
            }

            if (ClientRequest.GET_INCIDENTS.equals(type)) {
                return new ClientResponse(
                        true,
                        "Incidents loaded.",
                        incidentDAO.getAllIncidents());
            }

            if (ClientRequest.REGISTER_INCIDENT.equals(type)) {
                return handleRegisterIncident(request.getData());
            }

            if (ClientRequest.SEARCH_INCIDENTS.equals(type)) {
                return new ClientResponse(
                        true,
                        "Incidents loaded.",
                        incidentDAO.searchIncidents(request.getData()));
            }

            if (ClientRequest.ASSESS_INCIDENT_PRIORITY.equals(type)) {
                return handleAssessIncidentPriority(request.getData());
            }

            if (ClientRequest.ASSESS_INCIDENT_PRIORITY.equals(type)) {
                return handleAssessIncidentPriority(request.getData());
            }

            if (ClientRequest.GET_PUBLIC_ALERTS.equals(type)
                    || ClientRequest.GET_PUBLIC_ALERTS_FOR_PUBLIC_USER
                            .equals(type)) {
                return new ClientResponse(
                        true,
                        "Published public alerts loaded.",
                        publicAlertDAO.getPublishedAlerts());
            }

            if (ClientRequest.GET_ALL_PUBLIC_ALERTS.equals(type)) {
                return new ClientResponse(
                        true,
                        "Public alerts loaded.",
                        publicAlertDAO.getAllAlerts());
            }

            if (ClientRequest.CREATE_PUBLIC_ALERT.equals(type)) {
                return handleCreatePublicAlert(request.getData());
            }

            if (ClientRequest.PUBLISH_PUBLIC_ALERT.equals(type)) {
                return handleUpdatePublicAlertStatus(
                        request.getData(),
                        "PUBLISHED",
                        "Public alert published.");
            }

            if (ClientRequest.EXPIRE_PUBLIC_ALERT.equals(type)) {
                return handleUpdatePublicAlertStatus(
                        request.getData(),
                        "EXPIRED",
                        "Public alert expired.");
            }

            if (ClientRequest.GET_EVACUATION_SHELTERS.equals(type)) {
                return new ClientResponse(
                        true,
                        "Evacuation shelters loaded.",
                        shelterDAO.getAllShelters());
            }

            if (ClientRequest.ADD_EVACUATION_SHELTER.equals(type)) {
                return handleAddShelter(request.getData());
            }

            if (ClientRequest.UPDATE_EVACUATION_SHELTER.equals(type)) {
                return handleUpdateShelter(request.getData());
            }

            if (ClientRequest.GET_EMERGENCY_RESOURCES.equals(type)) {
                return new ClientResponse(
                        true,
                        "Emergency resources loaded.",
                        resourceDAO.getAllResources());
            }

            if (ClientRequest.GET_RESPONSE_AGENCIES.equals(type)) {
                return new ClientResponse(
                        true,
                        "Response agencies loaded.",
                        resourceDAO.getAllResponseAgencies());
            }

            if (ClientRequest.UPDATE_RESOURCE_AVAILABILITY.equals(type)) {
                return handleUpdateResourceAvailability(request.getData());
            }

            if (ClientRequest.DISPATCH_RESPONSE.equals(type)) {
                return handleDispatchResponse(request.getData());
            }

            if (ClientRequest.GET_RESPONSE_LOGS.equals(type)) {
                return new ClientResponse(
                        true,
                        "Response logs loaded.",
                        dispatchDAO.getAllDispatchRecords());
            }

            return ClientResponse.failure("Unknown request type: " + type);

        } catch (SQLException exception) {
            return ClientResponse.failure(
                    "Database error: " + exception.getMessage());
        } catch (Exception exception) {
            return ClientResponse.failure(
                    "Server error: " + exception.getMessage());
        }
    }

    private ClientResponse handleLogin(Map<String, String> data)
            throws SQLException {

        String username = data.get("username");
        String password = data.get("password");

        Map<String, String> userData = userDAO.authenticate(username, password);

        if (userData == null) {
            auditLogDAO.saveAuditLog(
                    username,
                    "LOGIN_FAILED",
                    "Invalid login attempt.");

            return ClientResponse.failure(
                    "Invalid username, password, or inactive account.");
        }

        auditLogDAO.saveAuditLog(
                username,
                "LOGIN_SUCCESS",
                "User logged in.");

        return new ClientResponse(true, "Login successful.", userData);
    }

    private ClientResponse handleRegisterPublicUser(Map<String, String> data)
            throws SQLException {

        String username = data.get("username");

        if (userDAO.usernameExists(username)) {
            return ClientResponse.failure("Username already exists.");
        }

        String userId = userDAO.generateNextUserId();

        boolean saved = userDAO.createUser(
                userId,
                data.get("fullName"),
                data.get("email"),
                username,
                data.get("password"),
                "PUBLIC_USER",
                "ACTIVE");

        if (!saved) {
            return ClientResponse.failure("Public user registration failed.");
        }

        auditLogDAO.saveAuditLog(
                username,
                "REGISTER_PUBLIC_USER",
                "New public user account created.");

        Map<String, String> responseData = new HashMap<>();
        responseData.put("userId", userId);

        return new ClientResponse(
                true,
                "Public user registered successfully.",
                responseData);
    }

    private ClientResponse handleAddUser(Map<String, String> data)
            throws SQLException {

        String username = data.get("username");

        if (userDAO.usernameExists(username)) {
            return ClientResponse.failure("Username already exists.");
        }

        String userId = userDAO.generateNextUserId();

        boolean saved = userDAO.createUser(
                userId,
                data.get("fullName"),
                data.get("email"),
                username,
                data.getOrDefault("password", "password123"),
                data.get("role"),
                data.get("accountStatus"));

        if (!saved) {
            return ClientResponse.failure("User account could not be added.");
        }

        Map<String, String> responseData = new HashMap<>();
        responseData.put("userId", userId);

        auditLogDAO.saveAuditLog(
                username,
                "ADD_USER",
                "User account created.");

        return new ClientResponse(
                true,
                "User account added.",
                responseData);
    }

    private ClientResponse handleUpdateUser(Map<String, String> data)
            throws SQLException {

        return ClientResponse.failure(
                "Update user backend method is not implemented yet.");
    }

    private ClientResponse handleSubmitDisasterReport(Map<String, String> data)
            throws SQLException {

        String reportId = disasterReportDAO.generateNextReportId();

        boolean saved = disasterReportDAO.saveReport(
                reportId,
                data.get("reporterName"),
                data.get("disasterType"),
                data.get("location"),
                data.get("description"),
                data.get("initialSeverity"));

        if (!saved) {
            return ClientResponse.failure(
                    "Disaster report could not be saved.");
        }

        auditLogDAO.saveAuditLog(
                data.get("username"),
                "SUBMIT_DISASTER_REPORT",
                "Report saved: " + reportId);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("reportId", reportId);
        responseData.put("nextReportId",
                disasterReportDAO.generateNextReportId());

        return new ClientResponse(
                true,
                "Disaster report submitted.",
                responseData);
    }

    private ClientResponse handleCreatePublicAlert(Map<String, String> data)
            throws SQLException {

        String alertId = publicAlertDAO.generateNextAlertId();

        boolean saved = publicAlertDAO.saveAlert(
                alertId,
                data.get("incidentId"),
                data.get("alertType"),
                data.get("affectedArea"),
                data.get("severityLevel"),
                data.get("alertMessage"),
                data.get("createdBy"),
                data.getOrDefault("alertStatus", "PUBLISHED"));

        if (!saved) {
            return ClientResponse.failure(
                    "Public alert could not be saved.");
        }

        auditLogDAO.saveAuditLog(
                data.get("createdBy"),
                "CREATE_PUBLIC_ALERT",
                "Alert saved: " + alertId);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("alertId", alertId);

        return new ClientResponse(
                true,
                "Public alert created successfully.",
                responseData);
    }

    private ClientResponse handleUpdatePublicAlertStatus(
            Map<String, String> data,
            String alertStatus,
            String successMessage) throws SQLException {

        boolean updated = publicAlertDAO.updateAlertStatus(
                data.get("alertId"),
                alertStatus);

        if (!updated) {
            return ClientResponse.failure(
                    "Public alert status could not be updated.");
        }

        return ClientResponse.success(successMessage);
    }

    private ClientResponse handleAddShelter(Map<String, String> data)
            throws SQLException {

        String shelterId = shelterDAO.generateNextShelterId();

        boolean saved = shelterDAO.saveShelter(
                shelterId,
                data.get("shelterName"),
                data.get("location"),
                Integer.parseInt(data.get("totalCapacity")),
                Integer.parseInt(data.get("currentOccupants")),
                data.get("shelterStatus"));

        if (!saved) {
            return ClientResponse.failure(
                    "Evacuation shelter could not be saved.");
        }

        auditLogDAO.saveAuditLog(
                data.get("username"),
                "ADD_EVACUATION_SHELTER",
                "Shelter saved: " + shelterId);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("shelterId", shelterId);

        return new ClientResponse(
                true,
                "Evacuation shelter added successfully.",
                responseData);
    }

    private ClientResponse handleUpdateShelter(Map<String, String> data)
            throws SQLException {

        boolean saved = shelterDAO.updateShelter(
                data.get("shelterId"),
                data.get("shelterName"),
                data.get("location"),
                Integer.parseInt(data.get("totalCapacity")),
                Integer.parseInt(data.get("currentOccupants")),
                data.get("shelterStatus"));

        if (!saved) {
            return ClientResponse.failure(
                    "Evacuation shelter could not be updated.");
        }

        auditLogDAO.saveAuditLog(
                data.get("username"),
                "UPDATE_EVACUATION_SHELTER",
                "Shelter updated: " + data.get("shelterId"));

        return ClientResponse.success(
                "Evacuation shelter updated successfully.");
    }

    private ClientResponse handleUpdateResourceAvailability(
            Map<String, String> data) throws SQLException {

        boolean updated = resourceDAO.updateResourceAvailability(
                data.get("resourceId"),
                data.get("action"));

        if (!updated) {
            return ClientResponse.failure(
                    "Resource availability could not be updated.");
        }

        return ClientResponse.success(
                "Resource availability updated.");
    }

    private ClientResponse handleDispatchResponse(Map<String, String> data)
            throws SQLException {

        String responseId = dispatchDAO.generateNextResponseId();

        boolean saved = dispatchDAO.saveDispatch(
                responseId,
                data.get("incidentId"),
                data.get("agencyId"),
                data.get("resourceId"),
                "DISPATCHED",
                data.get("dispatchNotes"));

        if (!saved) {
            return ClientResponse.failure(
                    "Emergency response could not be dispatched.");
        }

        Map<String, String> responseData = new HashMap<>();
        responseData.put("responseId", responseId);
        responseData.put("nextResponseId",
                dispatchDAO.generateNextResponseId());

        return new ClientResponse(
                true,
                "Emergency response dispatched.",
                responseData);
    }

    private ClientResponse handleAssessIncidentPriority(Map<String, String> data)
            throws SQLException {

        String incidentId = data.get("incidentId");

        if (incidentId == null || incidentId.trim().isEmpty()) {
            return ClientResponse.failure("Select an incident first.");
        }

        Map<String, String> incident = incidentDAO.findIncidentById(incidentId);

        if (incident == null || incident.isEmpty()) {
            return ClientResponse.failure("Incident not found.");
        }

        String severity = incident.getOrDefault("severity", "LOW");
        String affectedPeopleText = incident.getOrDefault("affectedPeople", "0");

        int affectedPeople;

        try {
            affectedPeople = Integer.parseInt(affectedPeopleText);
        } catch (NumberFormatException exception) {
            affectedPeople = 0;
        }

        int severityScore;

        switch (severity.toUpperCase()) {
            case "CRITICAL":
                severityScore = 4;
                break;
            case "HIGH":
                severityScore = 3;
                break;
            case "MEDIUM":
                severityScore = 2;
                break;
            default:
                severityScore = 1;
                break;
        }

        int riskScore = severityScore * affectedPeople;

        String priority;

        if (riskScore >= 300) {
            priority = "EMERGENCY";
        } else if (riskScore >= 150) {
            priority = "HIGH";
        } else if (riskScore >= 50) {
            priority = "MEDIUM";
        } else {
            priority = "LOW";
        }

        Map<String, String> responseData = new HashMap<>();
        responseData.put("severity", severity);
        responseData.put("riskScore", String.valueOf(riskScore));
        responseData.put("priority", priority);

        return new ClientResponse(
                true,
                "Incident assessed and priority recommended.",
                responseData);
    }

    private ClientResponse handleRegisterIncident(Map<String, String> data)
            throws SQLException {

        String reportId = data.get("reportId");

        if (reportId == null || reportId.trim().isEmpty()) {
            return ClientResponse.failure("Select a report ID first.");
        }

        int affectedPeople;

        try {
            affectedPeople = Integer.parseInt(data.get("affectedPeople"));
        } catch (NumberFormatException exception) {
            return ClientResponse.failure("Affected people must be numeric.");
        }

        String affectedArea = data.get("affectedArea");

        if (affectedArea == null || affectedArea.trim().isEmpty()) {
            return ClientResponse.failure("Affected area is required.");
        }

        String incidentId = incidentDAO.generateNextIncidentId();

        String severity = data.getOrDefault("severity", "HIGH");
        String priority = data.getOrDefault("priority", "HIGH");
        String status = data.getOrDefault("status", "REGISTERED");

        boolean saved = incidentDAO.saveIncident(
                incidentId,
                reportId,
                affectedPeople,
                affectedArea,
                severity,
                priority,
                status);

        if (!saved) {
            return ClientResponse.failure("Incident could not be registered.");
        }

        auditLogDAO.saveAuditLog(
                data.getOrDefault("username", "ecc"),
                "REGISTER_INCIDENT",
                "Incident registered: " + incidentId);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("incidentId", incidentId);
        responseData.put("nextIncidentId", incidentDAO.generateNextIncidentId());

        return new ClientResponse(
                true,
                "Incident registered successfully.",
                responseData);
    }

}
