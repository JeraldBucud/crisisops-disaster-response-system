package drsinitial.controller;

import drsinitial.model.DisasterReport;
import drsinitial.model.EmergencyResource;
import drsinitial.model.EmergencyResponse;
import drsinitial.model.Incident;
import drsinitial.model.IncidentUpdate;
import drsinitial.model.ResponseAgency;
import drsinitial.model.enums.DisasterType;
import drsinitial.model.enums.IncidentStatus;
import drsinitial.model.enums.PriorityLevel;
import drsinitial.model.enums.SeverityLevel;
import drsinitial.repository.ApplicationRepository;
import drsinitial.service.IncidentService;
import drsinitial.service.PriorityRecommendationService;
import drsinitial.session.UserSession;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import drsinitial.model.enums.UserRole;
import javafx.scene.control.TitledPane;

/**
 * Controls the main dashboard screen of the Disaster Response System.
 *
 * This controller manages JavaFX event handling and delegates incident-related
 * business logic to service classes.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class MainDashboardController {

    private final IncidentService incidentService = new IncidentService();

    private final PriorityRecommendationService priorityService
            = new PriorityRecommendationService();

    private final UserSession userSession = new UserSession();

    @FXML
    private VBox dashboardPane;
    @FXML
    private VBox reportPane;
    @FXML
    private VBox registerIncidentPane;
    @FXML
    private VBox severityPriorityPane;
    @FXML
    private VBox updateIncidentStatusPane;
    @FXML
    private VBox emergencyDispatchPane;
    @FXML
    private VBox responseLogPane;
    @FXML
    private VBox searchFilterPane;
    @FXML
    private VBox resourceCountersPane;

    @FXML
    private Button dashboardButton;
    @FXML
    private Button reportButton;
    @FXML
    private Button registerIncidentButton;
    @FXML
    private Button severityPriorityButton;
    @FXML
    private Button updateIncidentStatusButton;
    @FXML
    private Button emergencyDispatchButton;
    @FXML
    private Button responseLogButton;
    @FXML
    private Button searchFilterButton;
    @FXML
    private Button resourceCountersButton;
    @FXML
    private Button releaseResourceButton;
    @FXML
    private Button markUnavailableButton;
    @FXML
    private Button markMaintenanceButton;
    @FXML
    private Button restoreUnavailableButton;
    @FXML
    private Button restoreMaintenanceButton;

    @FXML
    private Label pageSubtitleLabel;
    @FXML
    private Label globalStatusLabel;
    @FXML
    private Label loggedInRoleLabel;

    @FXML
    private Label dashboardReportsCountLabel;
    @FXML
    private Label dashboardIncidentsCountLabel;
    @FXML
    private Label dashboardHighPriorityCountLabel;
    @FXML
    private Label dashboardAvailableResourcesCountLabel;

    @FXML
    private Label availableResourceCountLabel;
    @FXML
    private Label assignedResourceCountLabel;
    @FXML
    private Label unavailableResourceCountLabel;
    @FXML
    private Label maintenanceResourceCountLabel;

    @FXML
    private Label selectedRecordLabel;
    @FXML
    private Label reportStatusLabel;
    @FXML
    private Label incidentStatusLabel;
    @FXML
    private Label assessmentStatusLabel;
    @FXML
    private Label dispatchStatusLabel;
    @FXML
    private Label updateStatusLabel;
    @FXML
    private Label filterStatusLabel;
    @FXML
    private Label counterStatusLabel;

    @FXML
    private Label selectedReportTypeLabel;
    @FXML
    private Label selectedReportLocationLabel;
    @FXML
    private Label selectedReportSeverityLabel;

    @FXML
    private Label severityLevelDisplayLabel;
    @FXML
    private Label riskScoreLabel;
    @FXML
    private Label recommendedPriorityLabel;

    @FXML
    private ComboBox<DisasterType> disasterTypeComboBox;
    @FXML
    private ComboBox<SeverityLevel> initialSeverityComboBox;
    @FXML
    private ComboBox<String> incidentReportIdComboBox;
    @FXML
    private ComboBox<String> severityIncidentIdComboBox;
    @FXML
    private ComboBox<String> dispatchIncidentIdComboBox;
    @FXML
    private ComboBox<String> updateIncidentIdComboBox;
    @FXML
    private ComboBox<IncidentStatus> updateStatusComboBox;
    @FXML
    private ComboBox<ResponseAgency> agencyComboBox;
    @FXML
    private ComboBox<EmergencyResource> resourceComboBox;

    @FXML
    private ComboBox<DisasterType> filterDisasterTypeComboBox;
    @FXML
    private ComboBox<SeverityLevel> filterSeverityComboBox;
    @FXML
    private ComboBox<PriorityLevel> filterPriorityComboBox;
    @FXML
    private ComboBox<IncidentStatus> filterStatusComboBox;

    @FXML
    private TextField reportIdField;
    @FXML
    private TextField reporterNameField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField incidentIdField;
    @FXML
    private TextField affectedPeopleField;
    @FXML
    private TextField affectedAreaField;
    @FXML
    private TextField responseIdField;
    @FXML
    private TextField updateIdField;
    @FXML
    private TextField updatedByField;
    @FXML
    private TextField filterKeywordField;

    @FXML
    private TextArea descriptionArea;
    @FXML
    private TextArea selectedDescriptionArea;
    @FXML
    private TextArea selectedReportDescriptionArea;
    @FXML
    private TextArea dispatchNotesArea;
    @FXML
    private TextArea updateNotesArea;

    @FXML
    private TableView<Incident> incidentQueueTableView;
    @FXML
    private TableView<DisasterReport> submittedReportsTableView;
    @FXML
    private TableView<IncidentUpdate> incidentUpdateTableView;
    @FXML
    private TableView<EmergencyResponse> responseLogTableView;
    @FXML
    private TableView<Incident> filteredIncidentsTableView;
    @FXML
    private TableView<EmergencyResource> resourceTableView;
    @FXML
    private TableView<ResponseAgency> agencyTableView;

    @FXML
    private TitledPane incidentManagementPane;
    @FXML
    private TitledPane responseCoordinationPane;
    @FXML
    private TitledPane decisionSupportPane;

    /**
     * Initializes the dashboard screen.
     */
    @FXML
    private void initialize() {
        ApplicationRepository.loadSampleData();

        setupComboBoxes();
        setupTables();
        setupSelectionListeners();
        prepareGeneratedIds();
        refreshDashboardCounters();
        refreshResourceCounters();

        loggedInRoleLabel.setText(UserSession.getCurrentRoleDisplayName());

        applyRoleAccess();

        showDefaultPaneForRole();
    }

    /**
     * Sets up all combo box values used by the GUI.
     */
    private void setupComboBoxes() {

        disasterTypeComboBox.getItems().setAll(DisasterType.values());
        initialSeverityComboBox.getItems().setAll(SeverityLevel.values());
        updateStatusComboBox.getItems().setAll(IncidentStatus.values());

        filterDisasterTypeComboBox.getItems().setAll(DisasterType.values());
        filterSeverityComboBox.getItems().setAll(SeverityLevel.values());
        filterPriorityComboBox.getItems().setAll(PriorityLevel.values());
        filterStatusComboBox.getItems().setAll(IncidentStatus.values());

        agencyComboBox.setItems(ApplicationRepository.getResponseAgencies());
        resourceComboBox.setItems(ApplicationRepository.getEmergencyResources());

        refreshReportIdComboBox();
        refreshIncidentComboBoxes();
    }

    /**
     * Prepares generated IDs for input forms.
     */
    private void prepareGeneratedIds() {
        reportIdField.setText(ApplicationRepository.generateReportId());
        incidentIdField.setText(ApplicationRepository.generateIncidentId());
        responseIdField.setText(ApplicationRepository.generateResponseId());
        updateIdField.setText(ApplicationRepository.generateUpdateId());
    }

    /**
     * Refreshes the report ID combo box.
     */
    private void refreshReportIdComboBox() {
        incidentReportIdComboBox.getItems().clear();

        for (DisasterReport report : ApplicationRepository.getDisasterReports()) {
            incidentReportIdComboBox.getItems().add(report.getReportId());
        }
    }

    /**
     * Refreshes incident ID combo boxes.
     */
    private void refreshIncidentComboBoxes() {
        severityIncidentIdComboBox.getItems().clear();
        dispatchIncidentIdComboBox.getItems().clear();
        updateIncidentIdComboBox.getItems().clear();

        for (Incident incident : ApplicationRepository.getIncidents()) {
            severityIncidentIdComboBox.getItems().add(incident.getIncidentId());
            dispatchIncidentIdComboBox.getItems().add(incident.getIncidentId());
            updateIncidentIdComboBox.getItems().add(incident.getIncidentId());
        }
    }

    /**
     * Sets up all table data sources and column mappings.
     */
    private void setupTables() {
        incidentQueueTableView.setItems(incidentService.getActiveIncidents());
        submittedReportsTableView.setItems(
                ApplicationRepository.getDisasterReports());
        incidentUpdateTableView.setItems(
                ApplicationRepository.getIncidentUpdates());
        responseLogTableView.setItems(
                ApplicationRepository.getEmergencyResponses());
        filteredIncidentsTableView.setItems(
                ApplicationRepository.getIncidents());
        resourceTableView.setItems(
                ApplicationRepository.getEmergencyResources());
        agencyTableView.setItems(
                ApplicationRepository.getResponseAgencies());

        setupIncidentTable(incidentQueueTableView);
        setupIncidentTable(filteredIncidentsTableView);
        setupReportTable();
        setupIncidentUpdateTable();
        setupResponseLogTable();
        setupResourceTable();
        setupAgencyTable();
    }

    /**
     * Sets up the incident table columns.
     *
     * @param tableView incident table view
     */
    @SuppressWarnings("unchecked")
    private void setupIncidentTable(TableView<Incident> tableView) {
        TableColumn<Incident, String> idColumn
                = (TableColumn<Incident, String>) tableView.getColumns().get(0);
        TableColumn<Incident, String> typeColumn
                = (TableColumn<Incident, String>) tableView.getColumns().get(1);
        TableColumn<Incident, String> severityColumn
                = (TableColumn<Incident, String>) tableView.getColumns().get(2);
        TableColumn<Incident, String> locationColumn
                = (TableColumn<Incident, String>) tableView.getColumns().get(3);
        TableColumn<Incident, String> priorityColumn
                = (TableColumn<Incident, String>) tableView.getColumns().get(4);
        TableColumn<Incident, String> statusColumn
                = (TableColumn<Incident, String>) tableView.getColumns().get(5);

        idColumn.setCellValueFactory(cellData
                -> new SimpleStringProperty(
                        cellData.getValue().getIncidentId()
                ));

        typeColumn.setCellValueFactory(cellData -> {
            DisasterReport report = ApplicationRepository.findReportById(
                    cellData.getValue().getReportId());

            String value = "";

            if (report != null && report.getDisasterType() != null) {
                value = report.getDisasterType().toString();
            }

            return new SimpleStringProperty(value);
        });

        severityColumn.setCellValueFactory(cellData -> {
            Incident incident = cellData.getValue();

            String value = incident.getSeverityLevel() == null
                    ? incident.getSeverityDisplay()
                    : incident.getSeverityLevel().toString();

            return new SimpleStringProperty(value);
        });

        locationColumn.setCellValueFactory(cellData -> {
            DisasterReport report = ApplicationRepository.findReportById(
                    cellData.getValue().getReportId());

            String value = report == null
                    ? cellData.getValue().getAffectedArea()
                    : report.getLocation();

            return new SimpleStringProperty(value);
        });

        priorityColumn.setCellValueFactory(cellData -> {
            PriorityLevel priority = cellData.getValue().getPriorityLevel();

            String value = priority == null
                    ? "Pending"
                    : priority.toString();

            return new SimpleStringProperty(value);
        });

        statusColumn.setCellValueFactory(cellData -> {
            IncidentStatus status = cellData.getValue().getIncidentStatus();

            String value = status == null
                    ? ""
                    : status.toString();

            return new SimpleStringProperty(value);
        });
    }

    /**
     * Sets up submitted report table columns.
     */
    private void setupReportTable() {
        submittedReportsTableView.getColumns().get(0)
                .setCellValueFactory(new PropertyValueFactory<>("reportId"));
        submittedReportsTableView.getColumns().get(1)
                .setCellValueFactory(
                        new PropertyValueFactory<>("reporterName"));
        submittedReportsTableView.getColumns().get(2)
                .setCellValueFactory(
                        new PropertyValueFactory<>("disasterType"));
        submittedReportsTableView.getColumns().get(3)
                .setCellValueFactory(new PropertyValueFactory<>("location"));
        submittedReportsTableView.getColumns().get(4)
                .setCellValueFactory(
                        new PropertyValueFactory<>("initialSeverity"));
        submittedReportsTableView.getColumns().get(5)
                .setCellValueFactory(
                        new PropertyValueFactory<>("reportStatus"));
        submittedReportsTableView.getColumns().get(6)
                .setCellValueFactory(
                        new PropertyValueFactory<>("description"));
    }

    /**
     * Sets up incident update table columns.
     *
     * Expected FXML column order: Update ID, Incident ID, Status, Updated By,
     * Date Time, Notes.
     */
    private void setupIncidentUpdateTable() {
        incidentUpdateTableView.getColumns().get(0)
                .setCellValueFactory(new PropertyValueFactory<>("updateId"));
        incidentUpdateTableView.getColumns().get(1)
                .setCellValueFactory(new PropertyValueFactory<>("incidentId"));
        incidentUpdateTableView.getColumns().get(2)
                .setCellValueFactory(
                        new PropertyValueFactory<>("updatedStatus"));
        incidentUpdateTableView.getColumns().get(3)
                .setCellValueFactory(new PropertyValueFactory<>("updatedBy"));
        incidentUpdateTableView.getColumns().get(4)
                .setCellValueFactory(
                        new PropertyValueFactory<>("updateDateTime"));
        incidentUpdateTableView.getColumns().get(5)
                .setCellValueFactory(new PropertyValueFactory<>("updateNotes"));
    }

    /**
     * Sets up response log table columns.
     *
     * Expected FXML column order: Response ID, Incident ID, Agency, Resource,
     * Status, Dispatch Time, Notes.
     */
    @SuppressWarnings("unchecked")
    private void setupResponseLogTable() {
        TableColumn<EmergencyResponse, String> responseIdColumn
                = (TableColumn<EmergencyResponse, String>) responseLogTableView.getColumns().get(0);
        TableColumn<EmergencyResponse, String> incidentIdColumn
                = (TableColumn<EmergencyResponse, String>) responseLogTableView.getColumns().get(1);
        TableColumn<EmergencyResponse, String> agencyColumn
                = (TableColumn<EmergencyResponse, String>) responseLogTableView.getColumns().get(2);
        TableColumn<EmergencyResponse, String> resourceColumn
                = (TableColumn<EmergencyResponse, String>) responseLogTableView.getColumns().get(3);
        TableColumn<EmergencyResponse, String> statusColumn
                = (TableColumn<EmergencyResponse, String>) responseLogTableView.getColumns().get(4);
        TableColumn<EmergencyResponse, String> dateTimeColumn
                = (TableColumn<EmergencyResponse, String>) responseLogTableView.getColumns().get(5);
        TableColumn<EmergencyResponse, String> notesColumn
                = (TableColumn<EmergencyResponse, String>) responseLogTableView.getColumns().get(6);

        responseIdColumn.setCellValueFactory(
                new PropertyValueFactory<>("responseId"));

        incidentIdColumn.setCellValueFactory(cellData -> {
            Incident incident = cellData.getValue().getIncident();
            String value = incident == null ? "" : incident.getIncidentId();
            return new SimpleStringProperty(value);
        });

        agencyColumn.setCellValueFactory(cellData -> {
            ResponseAgency agency = cellData.getValue().getResponseAgency();
            String value = agency == null ? "" : agency.getAgencyName();
            return new SimpleStringProperty(value);
        });

        resourceColumn.setCellValueFactory(cellData -> {
            EmergencyResource resource
                    = cellData.getValue().getEmergencyResource();
            String value = resource == null ? "" : resource.getResourceName();
            return new SimpleStringProperty(value);
        });

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>("responseStatus"));

        dateTimeColumn.setCellValueFactory(
                new PropertyValueFactory<>("dispatchDateTime"));

        notesColumn.setCellValueFactory(
                new PropertyValueFactory<>("dispatchNotes"));
    }

    /**
     * Sets up resource table columns.
     *
     * Expected FXML column order: Resource ID, Resource Name, Type, Total,
     * Available, Assigned, Unavailable, Maintenance.
     */
    private void setupResourceTable() {
        resourceTableView.getColumns().get(0)
                .setCellValueFactory(new PropertyValueFactory<>("resourceId"));
        resourceTableView.getColumns().get(1)
                .setCellValueFactory(
                        new PropertyValueFactory<>("resourceName"));
        resourceTableView.getColumns().get(2)
                .setCellValueFactory(
                        new PropertyValueFactory<>("resourceType"));
        resourceTableView.getColumns().get(3)
                .setCellValueFactory(
                        new PropertyValueFactory<>("totalQuantity"));
        resourceTableView.getColumns().get(4)
                .setCellValueFactory(
                        new PropertyValueFactory<>("availableQuantity"));
        resourceTableView.getColumns().get(5)
                .setCellValueFactory(
                        new PropertyValueFactory<>("assignedQuantity"));
        resourceTableView.getColumns().get(6)
                .setCellValueFactory(
                        new PropertyValueFactory<>("unavailableQuantity"));
        resourceTableView.getColumns().get(7)
                .setCellValueFactory(
                        new PropertyValueFactory<>("maintenanceQuantity"));
    }

    /**
     * Sets up agency table columns.
     */
    private void setupAgencyTable() {
        agencyTableView.getColumns().get(0)
                .setCellValueFactory(new PropertyValueFactory<>("agencyId"));
        agencyTableView.getColumns().get(1)
                .setCellValueFactory(new PropertyValueFactory<>("agencyName"));
        agencyTableView.getColumns().get(2)
                .setCellValueFactory(new PropertyValueFactory<>("agencyType"));
        agencyTableView.getColumns().get(3)
                .setCellValueFactory(
                        new PropertyValueFactory<>("availabilityStatus"));
    }

    /**
     * Sets up table and combo box selection listeners.
     */
    private void setupSelectionListeners() {
        incidentQueueTableView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, incident) -> {
                    if (incident != null) {
                        showSelectedIncidentDetails(incident);
                    }
                });

        filteredIncidentsTableView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, incident) -> {
                    if (incident != null) {
                        showSelectedIncidentDetails(incident);
                    }
                });

        submittedReportsTableView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, report) -> {
                    if (report != null) {
                        selectedRecordLabel.setText(
                                "Report ID: " + report.getReportId()
                                + "\nReporter: " + report.getReporterName()
                                + "\nType: " + report.getDisasterType()
                                + "\nLocation: " + report.getLocation()
                                + "\nSeverity: " + report.getInitialSeverity()
                                + "\nStatus: " + report.getReportStatus()
                        );

                        selectedDescriptionArea.setText(
                                report.getDescription());
                    }
                });

        incidentReportIdComboBox.valueProperty()
                .addListener((observable, oldValue, reportId) -> {
                    if (reportId != null) {
                        DisasterReport report
                                = ApplicationRepository.findReportById(reportId);

                        if (report != null) {
                            selectedReportTypeLabel.setText(
                                    report.getDisasterType().toString());
                            selectedReportLocationLabel.setText(
                                    report.getLocation());
                            selectedReportSeverityLabel.setText(
                                    report.getInitialSeverity().toString());
                            selectedReportDescriptionArea.setText(
                                    report.getDescription());
                        }
                    }
                });
    }

    /**
     * Displays selected incident details.
     *
     * @param incident selected incident
     */
    private void showSelectedIncidentDetails(Incident incident) {
        DisasterReport report = ApplicationRepository.findReportById(
                incident.getReportId());

        String reportDetails = "";
        String description = "No linked report description found.";

        if (report != null) {
            reportDetails = "\nReport ID: " + report.getReportId()
                    + "\nReporter: " + report.getReporterName()
                    + "\nDisaster Type: " + report.getDisasterType()
                    + "\nReport Severity: " + report.getInitialSeverity();
            description = report.getDescription();
        }

        selectedRecordLabel.setText(
                "Incident ID: " + incident.getIncidentId()
                + reportDetails
                + "\nAffected Area: " + incident.getAffectedArea()
                + "\nAffected People: " + incident.getAffectedPeople()
                + "\nSeverity: " + incident.getSeverityDisplay()
                + "\nPriority: " + incident.getPriorityLevel()
                + "\nStatus: " + incident.getIncidentStatus()
        );

        selectedDescriptionArea.setText(description);
    }

    /**
     * Refreshes incident tables and related dashboard values.
     */
    private void refreshIncidentTables() {
        incidentQueueTableView.setItems(incidentService.getActiveIncidents());
        filteredIncidentsTableView.setItems(ApplicationRepository.getIncidents());

        incidentQueueTableView.refresh();
        filteredIncidentsTableView.refresh();

        refreshIncidentComboBoxes();
        refreshDashboardCounters();

        String selectedIncidentId = severityIncidentIdComboBox.getValue();

        if (selectedIncidentId != null) {
            Incident selectedIncident
                    = ApplicationRepository.findIncidentById(selectedIncidentId);

            if (selectedIncident != null) {
                showSelectedIncidentDetails(selectedIncident);
            }
        }
    }

    /**
     * Refreshes dashboard counters.
     */
    private void refreshDashboardCounters() {
        dashboardReportsCountLabel.setText(String.valueOf(
                ApplicationRepository.getDisasterReports().size()));

        dashboardIncidentsCountLabel.setText(String.valueOf(
                ApplicationRepository.getIncidents().size()));

        int highPriorityCount = 0;
        int availableUnits = 0;

        for (Incident incident : ApplicationRepository.getIncidents()) {
            if (incident.getPriorityLevel() == PriorityLevel.HIGH
                    || incident.getPriorityLevel() == PriorityLevel.EMERGENCY) {
                highPriorityCount++;
            }
        }

        for (EmergencyResource resource
                : ApplicationRepository.getEmergencyResources()) {
            availableUnits += resource.getAvailableQuantity();
        }

        dashboardHighPriorityCountLabel.setText(
                String.valueOf(highPriorityCount));
        dashboardAvailableResourcesCountLabel.setText(
                String.valueOf(availableUnits));
    }

    /**
     * Refreshes resource counters.
     */
    private void refreshResourceCounters() {
        int available = 0;
        int assigned = 0;
        int unavailable = 0;
        int maintenance = 0;

        for (EmergencyResource resource
                : ApplicationRepository.getEmergencyResources()) {
            available += resource.getAvailableQuantity();
            assigned += resource.getAssignedQuantity();
            unavailable += resource.getUnavailableQuantity();
            maintenance += resource.getMaintenanceQuantity();
        }

        availableResourceCountLabel.setText(String.valueOf(available));
        assignedResourceCountLabel.setText(String.valueOf(assigned));
        unavailableResourceCountLabel.setText(String.valueOf(unavailable));
        maintenanceResourceCountLabel.setText(String.valueOf(maintenance));
    }

    /**
     * Handles disaster report submission.
     */
    @FXML
    private void handleSubmitReport() {
        DisasterReport report = new DisasterReport(
                reportIdField.getText(),
                reporterNameField.getText(),
                disasterTypeComboBox.getValue(),
                locationField.getText(),
                descriptionArea.getText(),
                initialSeverityComboBox.getValue()
        );

        if (!report.validateReport()) {
            reportStatusLabel.setText("Please complete all required fields.");
            return;
        }

        ApplicationRepository.addDisasterReport(report);
        submittedReportsTableView.refresh();
        refreshReportIdComboBox();
        refreshDashboardCounters();

        reportStatusLabel.setText("Report submitted successfully.");
        globalStatusLabel.setText("System Status: Report submitted.");

        reporterNameField.clear();
        locationField.clear();
        descriptionArea.clear();
        disasterTypeComboBox.getSelectionModel().clearSelection();
        initialSeverityComboBox.getSelectionModel().clearSelection();
        reportIdField.setText(ApplicationRepository.generateReportId());
    }

    /**
     * Handles incident registration.
     */
    @FXML
    private void handleRegisterIncident() {
        String reportId = incidentReportIdComboBox.getValue();

        if (!hasOperationalAccess()) {
            globalStatusLabel.setText("System Status: Access denied.");
            return;
        }

        if (reportId == null) {
            incidentStatusLabel.setText("Select a report ID first.");
            return;
        }

        if (affectedPeopleField.getText().isBlank()
                || affectedAreaField.getText().isBlank()) {
            incidentStatusLabel.setText("Complete all incident fields.");
            return;
        }

        int affectedPeople;

        try {
            affectedPeople = Integer.parseInt(affectedPeopleField.getText());
        } catch (NumberFormatException exception) {
            incidentStatusLabel.setText("Affected people must be numeric.");
            return;
        }

        incidentService.registerIncident(
                incidentIdField.getText(),
                reportId,
                affectedPeople,
                affectedAreaField.getText()
        );

        refreshIncidentTables();

        incidentStatusLabel.setText("Incident registered successfully.");
        globalStatusLabel.setText("System Status: Incident registered.");

        incidentReportIdComboBox.getSelectionModel().clearSelection();
        affectedPeopleField.clear();
        affectedAreaField.clear();
        selectedReportTypeLabel.setText("Select a report first.");
        selectedReportLocationLabel.setText("Select a report first.");
        selectedReportSeverityLabel.setText("Select a report first.");
        selectedReportDescriptionArea.clear();
        incidentIdField.setText(ApplicationRepository.generateIncidentId());
    }

    /**
     * Handles severity assessment and priority recommendation.
     */
    @FXML
    private void handleAssessAndPrioritise() {
        String incidentId = severityIncidentIdComboBox.getValue();

        if (!hasOperationalAccess()) {
            globalStatusLabel.setText("System Status: Access denied.");
            return;
        }

        if (incidentId == null) {
            assessmentStatusLabel.setText("Select an incident first.");
            return;
        }

        Incident incident = ApplicationRepository.findIncidentById(incidentId);

        if (incident == null) {
            assessmentStatusLabel.setText("Incident not found.");
            return;
        }

        DisasterReport report = ApplicationRepository.findReportById(
                incident.getReportId());

        if (report == null) {
            assessmentStatusLabel.setText("Linked report not found.");
            return;
        }

        SeverityLevel severity = report.getInitialSeverity();

        boolean severityUpdated = incidentService.assessIncident(
                incident,
                severity
        );

        if (!severityUpdated) {
            assessmentStatusLabel.setText("Severity assessment failed.");
            return;
        }

        PriorityLevel priority = priorityService.recommendPriority(incident);
        double riskScore = priorityService.calculateRiskScore(incident);

        boolean priorityUpdated = incidentService.prioritiseIncident(
                incident,
                priority
        );

        if (!priorityUpdated) {
            assessmentStatusLabel.setText("Priority update failed.");
            return;
        }

        severityLevelDisplayLabel.setText(severity.toString());
        riskScoreLabel.setText(String.valueOf(riskScore));
        recommendedPriorityLabel.setText(priority.toString());

        refreshIncidentTables();
        showSelectedIncidentDetails(incident);

        assessmentStatusLabel.setText("Assessment completed.");
        globalStatusLabel.setText(
                "System Status: Incident assessed and prioritised.");
    }

    /**
     * Handles emergency response dispatch.
     */
    @FXML
    private void handleDispatchResponse() {
        if (dispatchIncidentIdComboBox.getValue() == null
                || agencyComboBox.getValue() == null
                || resourceComboBox.getValue() == null
                || dispatchNotesArea.getText().isBlank()) {
            dispatchStatusLabel.setText("Complete all dispatch fields.");
            return;
        }

        Incident incident = ApplicationRepository.findIncidentById(
                dispatchIncidentIdComboBox.getValue());

        if (!hasOperationalAccess()) {
            globalStatusLabel.setText("System Status: Access denied.");
            return;
        }

        if (incident == null) {
            dispatchStatusLabel.setText("Incident not found.");
            return;
        }

        EmergencyResource resource = resourceComboBox.getValue();

        if (!resource.checkAvailability()) {
            dispatchStatusLabel.setText("Selected resource is unavailable.");
            return;
        }

        EmergencyResponse response = new EmergencyResponse(
                responseIdField.getText(),
                incident,
                agencyComboBox.getValue(),
                resource,
                dispatchNotesArea.getText()
        );

        boolean dispatched = response.dispatchResponse();

        if (!dispatched) {
            dispatchStatusLabel.setText("Dispatch failed.");
            return;
        }

        ApplicationRepository.addEmergencyResponse(response);
        incidentService.dispatchIncident(incident);

        IncidentUpdate update = new IncidentUpdate(
                ApplicationRepository.generateUpdateId(),
                incident.getIncidentId(),
                "Resource dispatched: "
                + resource.getResourceName()
                + " assigned by "
                + agencyComboBox.getValue().getAgencyName()
                + ". Notes: "
                + dispatchNotesArea.getText(),
                "Emergency Control Centre",
                IncidentStatus.DISPATCHED
        );

        ApplicationRepository.addIncidentUpdate(update);

        responseLogTableView.refresh();
        incidentUpdateTableView.refresh();
        resourceTableView.refresh();

        refreshIncidentTables();
        refreshResourceCounters();
        refreshDashboardCounters();

        dispatchStatusLabel.setText("Response dispatched successfully.");
        globalStatusLabel.setText("System Status: Response dispatched.");

        dispatchIncidentIdComboBox.getSelectionModel().clearSelection();
        agencyComboBox.getSelectionModel().clearSelection();
        resourceComboBox.getSelectionModel().clearSelection();
        dispatchNotesArea.clear();
        responseIdField.setText(ApplicationRepository.generateResponseId());
        updateIdField.setText(ApplicationRepository.generateUpdateId());
    }

    /**
     * Handles incident status updates.
     */
    @FXML
    private void handleUpdateIncidentStatus() {
        if (updateIncidentIdComboBox.getValue() == null
                || updateStatusComboBox.getValue() == null
                || updatedByField.getText().isBlank()
                || updateNotesArea.getText().isBlank()) {
            updateStatusLabel.setText("Complete all update fields.");
            return;
        }

        Incident incident = ApplicationRepository.findIncidentById(
                updateIncidentIdComboBox.getValue());

        if (!hasOperationalAccess()) {
            globalStatusLabel.setText("System Status: Access denied.");
            return;
        }

        if (incident == null) {
            updateStatusLabel.setText("Incident not found.");
            return;
        }

        incidentService.updateIncidentStatus(
                incident,
                updateStatusComboBox.getValue()
        );

        IncidentUpdate update = new IncidentUpdate(
                updateIdField.getText(),
                incident.getIncidentId(),
                updateNotesArea.getText(),
                updatedByField.getText(),
                updateStatusComboBox.getValue()
        );

        ApplicationRepository.addIncidentUpdate(update);
        incidentUpdateTableView.refresh();
        refreshIncidentTables();

        updateStatusLabel.setText("Incident status updated successfully.");
        globalStatusLabel.setText("System Status: Incident status updated.");

        updateIncidentIdComboBox.getSelectionModel().clearSelection();
        updateStatusComboBox.getSelectionModel().clearSelection();
        updatedByField.clear();
        updateNotesArea.clear();
        updateIdField.setText(ApplicationRepository.generateUpdateId());
    }

    /**
     * Applies incident search and filter criteria.
     */
    @FXML
    private void handleApplyFilter() {
        ObservableList<Incident> filtered = FXCollections.observableArrayList();
        String keyword = filterKeywordField.getText().toLowerCase().trim();

        for (Incident incident : ApplicationRepository.getIncidents()) {
            DisasterReport report = ApplicationRepository.findReportById(
                    incident.getReportId());

            boolean matchesKeyword = keyword.isBlank()
                    || incident.getIncidentId().toLowerCase().contains(keyword)
                    || incident.getAffectedArea().toLowerCase()
                            .contains(keyword);

            boolean matchesPriority
                    = filterPriorityComboBox.getValue() == null
                    || incident.getPriorityLevel()
                    == filterPriorityComboBox.getValue();

            boolean matchesStatus
                    = filterStatusComboBox.getValue() == null
                    || incident.getIncidentStatus()
                    == filterStatusComboBox.getValue();

            boolean matchesDisasterType
                    = filterDisasterTypeComboBox.getValue() == null
                    || (report != null
                    && report.getDisasterType()
                    == filterDisasterTypeComboBox.getValue());

            boolean matchesSeverity
                    = filterSeverityComboBox.getValue() == null
                    || (report != null
                    && report.getInitialSeverity()
                    == filterSeverityComboBox.getValue());

            if (matchesKeyword
                    && matchesPriority
                    && matchesStatus
                    && matchesDisasterType
                    && matchesSeverity) {
                filtered.add(incident);
            }
        }

        filteredIncidentsTableView.setItems(filtered);
        filterStatusLabel.setText(filtered.size() + " incidents found.");

        if (!hasOperationalAccess()) {
            globalStatusLabel.setText("System Status: Access denied.");
            return;
        }
    }

    /**
     * Resets incident filters.
     */
    @FXML
    private void handleResetFilter() {
        filteredIncidentsTableView.setItems(ApplicationRepository.getIncidents());
        filterKeywordField.clear();
        filterDisasterTypeComboBox.getSelectionModel().clearSelection();
        filterSeverityComboBox.getSelectionModel().clearSelection();
        filterPriorityComboBox.getSelectionModel().clearSelection();
        filterStatusComboBox.getSelectionModel().clearSelection();
        filterStatusLabel.setText("Filters reset.");
    }

    /**
     * Releases one assigned unit from the selected resource.
     */
    @FXML
    private void handleReleaseResource() {
        EmergencyResource resource
                = resourceTableView.getSelectionModel().getSelectedItem();

        if (!hasOperationalAccess()) {
            globalStatusLabel.setText("System Status: Access denied.");
            return;
        }

        if (resource == null) {
            counterStatusLabel.setText("Select a resource first.");
            return;
        }

        boolean released = resource.releaseResource();

        if (!released) {
            counterStatusLabel.setText(
                    "No assigned units available to release.");
            return;
        }

        resourceTableView.refresh();
        refreshResourceCounters();
        refreshDashboardCounters();

        counterStatusLabel.setText(
                "One assigned unit released for "
                + resource.getResourceName()
                + ".");
    }

    /**
     * Marks one available unit from the selected resource as unavailable.
     */
    @FXML
    private void handleMarkResourceUnavailable() {
        EmergencyResource resource
                = resourceTableView.getSelectionModel().getSelectedItem();

        if (!hasOperationalAccess()) {
            globalStatusLabel.setText("System Status: Access denied.");
            return;
        }

        if (resource == null) {
            counterStatusLabel.setText("Select a resource first.");
            return;
        }

        boolean updated = resource.markOneUnavailable();

        if (!updated) {
            counterStatusLabel.setText(
                    "No available units to mark unavailable.");
            return;
        }

        resourceTableView.refresh();
        refreshResourceCounters();
        refreshDashboardCounters();

        counterStatusLabel.setText(
                "One unit marked unavailable for "
                + resource.getResourceName()
                + ".");
    }

    /**
     * Marks one available unit from the selected resource as under maintenance.
     */
    @FXML
    private void handleMarkResourceMaintenance() {
        EmergencyResource resource
                = resourceTableView.getSelectionModel().getSelectedItem();

        if (!hasOperationalAccess()) {
            globalStatusLabel.setText("System Status: Access denied.");
            return;
        }

        if (resource == null) {
            counterStatusLabel.setText("Select a resource first.");
            return;
        }

        boolean updated = resource.markOneMaintenance();

        if (!updated) {
            counterStatusLabel.setText(
                    "No available units to mark for maintenance.");
            return;
        }

        resourceTableView.refresh();
        refreshResourceCounters();
        refreshDashboardCounters();

        counterStatusLabel.setText(
                "One unit marked under maintenance for "
                + resource.getResourceName()
                + ".");
    }

    /**
     * Restores one unavailable unit from the selected resource back to
     * available.
     */
    @FXML
    private void handleRestoreUnavailableResource() {
        EmergencyResource resource
                = resourceTableView.getSelectionModel().getSelectedItem();

        if (!hasOperationalAccess()) {
            globalStatusLabel.setText("System Status: Access denied.");
            return;
        }

        if (resource == null) {
            counterStatusLabel.setText("Select a resource first.");
            return;
        }

        boolean restored = resource.restoreOneUnavailable();

        if (!restored) {
            counterStatusLabel.setText(
                    "No unavailable units available to restore.");
            return;
        }

        resourceTableView.refresh();
        refreshResourceCounters();
        refreshDashboardCounters();

        counterStatusLabel.setText(
                "One unavailable unit restored for "
                + resource.getResourceName()
                + ".");
    }

    /**
     * Restores one maintenance unit from the selected resource back to
     * available.
     */
    @FXML
    private void handleRestoreMaintenanceResource() {
        EmergencyResource resource
                = resourceTableView.getSelectionModel().getSelectedItem();

        if (!hasOperationalAccess()) {
            globalStatusLabel.setText("System Status: Access denied.");
            return;
        }

        if (resource == null) {
            counterStatusLabel.setText("Select a resource first.");
            return;
        }

        boolean restored = resource.restoreOneMaintenance();

        if (!restored) {
            counterStatusLabel.setText(
                    "No maintenance units available to restore.");
            return;
        }

        resourceTableView.refresh();
        refreshResourceCounters();
        refreshDashboardCounters();

        counterStatusLabel.setText(
                "One maintenance unit restored for "
                + resource.getResourceName()
                + ".");
    }

    /**
     * Logs out the current prototype user.
     */
    @FXML
    private void handleLogout() {
        try {
            UserSession.logout();

            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(
                    getClass().getResource("/drsinitial/view/LoginView.fxml"));

            javafx.scene.Scene scene = new javafx.scene.Scene(root);

            javafx.stage.Stage stage
                    = (javafx.stage.Stage) dashboardPane.getScene().getWindow();

            stage.setTitle("DRS-Enhanced Login");
            stage.setScene(scene);
            stage.show();

        } catch (java.io.IOException exception) {
            globalStatusLabel.setText("System Status: Unable to log out.");
        }
    }

    /**
     * Shows the dashboard pane.
     */
    @FXML
    private void showDashboard() {
        showOnlyPane(dashboardPane);
        pageSubtitleLabel.setText("Dashboard Overview");
        setActiveButton(dashboardButton);
        refreshDashboardCounters();
    }

    /**
     * Shows the report disaster pane.
     */
    @FXML
    private void showReportDisaster() {
        showOnlyPane(reportPane);
        pageSubtitleLabel.setText("UCA2-UC1 Report Disaster");
        setActiveButton(reportButton);
    }

    /**
     * Shows the register incident pane.
     */
    @FXML
    private void showRegisterIncident() {
        showOnlyPane(registerIncidentPane);
        pageSubtitleLabel.setText("UCA2-UC2 Validate and Register Incident");
        setActiveButton(registerIncidentButton);
    }

    /**
     * Shows the severity and priority pane.
     */
    @FXML
    private void showSeverityPriority() {
        showOnlyPane(severityPriorityPane);
        pageSubtitleLabel.setText("UCA2-UC3 and UCA2-UC4 Severity and Priority");
        setActiveButton(severityPriorityButton);
    }

    /**
     * Shows the update incident status pane.
     */
    @FXML
    private void showUpdateIncidentStatus() {
        showOnlyPane(updateIncidentStatusPane);
        pageSubtitleLabel.setText("Update Incident Status");
        setActiveButton(updateIncidentStatusButton);
    }

    /**
     * Shows the emergency dispatch pane.
     */
    @FXML
    private void showEmergencyDispatch() {
        showOnlyPane(emergencyDispatchPane);
        pageSubtitleLabel.setText("UCA2-UC6 Emergency Dispatch");
        setActiveButton(emergencyDispatchButton);
    }

    /**
     * Shows the response log pane.
     */
    @FXML
    private void showResponseLog() {
        showOnlyPane(responseLogPane);
        pageSubtitleLabel.setText("Emergency Response Log");
        setActiveButton(responseLogButton);
        responseLogTableView.refresh();
    }

    /**
     * Shows the search and filter pane.
     */
    @FXML
    private void showSearchFilter() {
        showOnlyPane(searchFilterPane);
        pageSubtitleLabel.setText("Incident Search and Filter");
        setActiveButton(searchFilterButton);
    }

    /**
     * Shows the resource counters pane.
     */
    @FXML
    private void showResourceCounters() {
        showOnlyPane(resourceCountersPane);
        pageSubtitleLabel.setText("Emergency Resource Availability Tracker");
        setActiveButton(resourceCountersButton);
        resourceTableView.refresh();
        refreshResourceCounters();
    }

    /**
     * Shows only the selected page pane.
     *
     * @param activePane pane to show
     */
    private void showOnlyPane(VBox activePane) {
        VBox[] panes = {
            dashboardPane,
            reportPane,
            registerIncidentPane,
            severityPriorityPane,
            updateIncidentStatusPane,
            emergencyDispatchPane,
            responseLogPane,
            searchFilterPane,
            resourceCountersPane
        };

        for (VBox pane : panes) {
            pane.setVisible(false);
            pane.setManaged(false);
        }

        activePane.setVisible(true);
        activePane.setManaged(true);
    }

    /**
     * Updates active navigation button styling.
     *
     * @param activeButton active navigation button
     */
    private void setActiveButton(Button activeButton) {
        Button[] buttons = {
            dashboardButton,
            reportButton,
            registerIncidentButton,
            severityPriorityButton,
            updateIncidentStatusButton,
            emergencyDispatchButton,
            responseLogButton,
            searchFilterButton,
            resourceCountersButton
        };

        for (Button button : buttons) {
            button.getStyleClass().remove("nav-button-active");
            button.getStyleClass().remove("sub-nav-button-active");
            button.getStyleClass().remove("nav-button");
            button.getStyleClass().remove("sub-nav-button");

            if (button == dashboardButton || button == reportButton) {
                button.getStyleClass().add("nav-button");
            } else {
                button.getStyleClass().add("sub-nav-button");
            }
        }

        activeButton.getStyleClass().remove("nav-button");
        activeButton.getStyleClass().remove("sub-nav-button");

        if (activeButton == dashboardButton || activeButton == reportButton) {
            activeButton.getStyleClass().add("nav-button-active");
        } else {
            activeButton.getStyleClass().add("sub-nav-button-active");
        }
    }

    /**
     * Applies role-based access control to the dashboard menu.
     */
    private void applyRoleAccess() {
        UserRole currentRole = UserSession.getCurrentRole();

        if (currentRole == UserRole.PUBLIC_USER) {
            applyPublicUserAccess();
            return;
        }

        if (currentRole == UserRole.EMERGENCY_CONTROL_CENTRE) {
            applyEmergencyControlCentreAccess();
            return;
        }

        if (currentRole == UserRole.SYSTEM_ADMINISTRATOR) {
            applySystemAdministratorAccess();
        }
    }

    /**
     * Applies Public User access.
     *
     * Public users can only report disasters and view public alerts. Public
     * Alerts will be added in the next feature screen task.
     */
    private void applyPublicUserAccess() {
        setButtonAccess(dashboardButton, false);
        setButtonAccess(reportButton, true);

        setTitledPaneAccess(incidentManagementPane, false);
        setButtonAccess(registerIncidentButton, false);
        setButtonAccess(severityPriorityButton, false);
        setButtonAccess(updateIncidentStatusButton, false);

        setTitledPaneAccess(responseCoordinationPane, false);
        setButtonAccess(emergencyDispatchButton, false);
        setButtonAccess(responseLogButton, false);

        setTitledPaneAccess(decisionSupportPane, false);
        setButtonAccess(searchFilterButton, false);
        setButtonAccess(resourceCountersButton, false);

        globalStatusLabel.setText(
                "System Status: Public User access applied.");
    }

    /**
     * Applies Emergency Control Centre access.
     *
     * Emergency Control Centre users can access operational screens.
     */
    private void applyEmergencyControlCentreAccess() {
        setButtonAccess(dashboardButton, true);
        setButtonAccess(reportButton, true);
        setButtonAccess(registerIncidentButton, true);
        setButtonAccess(severityPriorityButton, true);
        setButtonAccess(updateIncidentStatusButton, true);
        setButtonAccess(emergencyDispatchButton, true);
        setButtonAccess(responseLogButton, true);
        setButtonAccess(searchFilterButton, true);
        setButtonAccess(resourceCountersButton, true);
        setTitledPaneAccess(incidentManagementPane, true);
        setTitledPaneAccess(responseCoordinationPane, true);
        setTitledPaneAccess(decisionSupportPane, true);

        globalStatusLabel.setText(
                "System Status: Emergency Control Centre access applied.");
    }

    /**
     * Applies System Administrator access.
     *
     * System administrators can access all available screens.
     */
    private void applySystemAdministratorAccess() {
        setButtonAccess(dashboardButton, true);
        setButtonAccess(reportButton, true);
        setButtonAccess(registerIncidentButton, true);
        setButtonAccess(severityPriorityButton, true);
        setButtonAccess(updateIncidentStatusButton, true);
        setButtonAccess(emergencyDispatchButton, true);
        setButtonAccess(responseLogButton, true);
        setButtonAccess(searchFilterButton, true);
        setButtonAccess(resourceCountersButton, true);
        setTitledPaneAccess(incidentManagementPane, true);
        setTitledPaneAccess(responseCoordinationPane, true);
        setTitledPaneAccess(decisionSupportPane, true);

        globalStatusLabel.setText(
                "System Status: System Administrator access applied.");
    }

    /**
     * Shows or hides a navigation button.
     *
     * @param button navigation button
     * @param allowed true if the button should be shown
     */
    private void setButtonAccess(Button button, boolean allowed) {
        button.setVisible(allowed);
        button.setManaged(allowed);
        button.setDisable(!allowed);
    }

    /**
     * Shows or hides a sidebar titled pane.
     *
     * @param pane sidebar titled pane
     * @param allowed true if the pane should be shown
     */
    private void setTitledPaneAccess(TitledPane pane, boolean allowed) {
        pane.setVisible(allowed);
        pane.setManaged(allowed);
        pane.setDisable(!allowed);
    }

    /**
     * Shows the correct first screen after login.
     */
    private void showDefaultPaneForRole() {
        UserRole currentRole = UserSession.getCurrentRole();

        if (currentRole == UserRole.PUBLIC_USER) {
            showReportDisaster();
            return;
        }

        showDashboard();
    }

    /**
     * Checks whether the current user is allowed to use restricted functions.
     *
     * @return true if the user has operational access
     */
    private boolean hasOperationalAccess() {
        UserRole currentRole = UserSession.getCurrentRole();

        return currentRole == UserRole.EMERGENCY_CONTROL_CENTRE
                || currentRole == UserRole.SYSTEM_ADMINISTRATOR;
    }
}
