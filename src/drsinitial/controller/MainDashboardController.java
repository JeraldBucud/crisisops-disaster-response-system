package drsinitial.controller;

import java.util.Map;
import drsinitial.client.BackendClient;
import drsinitial.client.ClientResponse;
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
import drsinitial.model.EvacuationShelter;
import java.time.LocalDateTime;
import drsinitial.model.PublicAlert;
import drsinitial.model.User;
import drsinitial.service.IncidentService;
import drsinitial.service.PriorityRecommendationService;
import drsinitial.service.ShelterAvailabilityService;
import drsinitial.service.PublicAlertService;

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

    private final BackendClient backendClient = new BackendClient();

    private final IncidentService incidentService = new IncidentService();

    private final PriorityRecommendationService priorityService
            = new PriorityRecommendationService();

    private final ShelterAvailabilityService shelterAvailabilityService
            = new ShelterAvailabilityService();

    private final PublicAlertService publicAlertService
            = new PublicAlertService();

    private final UserSession userSession = new UserSession();

    private final ObservableList<EvacuationShelter> evacuationShelters
            = ApplicationRepository.getEvacuationShelters();

    private final ObservableList<PublicAlert> publicAlerts
            = ApplicationRepository.getPublicAlerts();

    private final ObservableList<User> systemUsers
            = ApplicationRepository.getSystemUsers();

    private final ObservableList<PublicAlert> filteredPublicAlerts
            = FXCollections.observableArrayList();

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
    private VBox evacuationShelterPane;
    @FXML
    private VBox publicAlertPane;
    @FXML
    private VBox publicAlertFormPane;
    @FXML
    private VBox userManagementPane;

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
    private Button evacuationShelterButton;
    @FXML
    private Button publicAlertButton;
    @FXML
    private Button createAlertButton;
    @FXML
    private Button publishAlertButton;
    @FXML
    private Button clearAlertButton;
    @FXML
    private Button userManagementButton;
    @FXML
    private Button addShelterButton;
    @FXML
    private Button updateShelterButton;
    @FXML
    private Button clearShelterButton;
    @FXML
    private Button expireAlertButton;

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
    private Label totalShelterCountLabel;
    @FXML
    private Label availableShelterCountLabel;
    @FXML
    private Label fullShelterCountLabel;
    @FXML
    private Label freeShelterSpaceCountLabel;
    @FXML
    private Label shelterStatusLabel;
    @FXML
    private Label publicAlertStatusLabel;
    @FXML
    private Label userManagementStatusLabel;
    @FXML
    private Label publicAlertTitleLabel;

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
    private ComboBox<String> shelterStatusComboBox;

    @FXML
    private ComboBox<String> alertIncidentIdComboBox;
    @FXML
    private ComboBox<String> alertTypeComboBox;
    @FXML
    private ComboBox<String> alertSeverityComboBox;
    @FXML
    private ComboBox<String> alertStatusComboBox;
    @FXML
    private ComboBox<String> adminUserRoleComboBox;
    @FXML
    private ComboBox<String> adminAccountStatusComboBox;
    @FXML
    private ComboBox<String> publicAlertStatusFilterComboBox;

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
    private TextField adminUserIdField;
    @FXML
    private TextField adminFullNameField;
    @FXML
    private TextField adminEmailField;
    @FXML
    private TextField adminUsernameField;

    @FXML
    private TextField shelterIdField;
    @FXML
    private TextField shelterNameField;
    @FXML
    private TextField shelterLocationField;
    @FXML
    private TextField shelterCapacityField;
    @FXML
    private TextField shelterCurrentOccupantsField;

    @FXML
    private TextField alertIdField;
    @FXML
    private TextField alertAffectedAreaField;
    @FXML
    private TextField publicAlertSearchField;

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
    private TextArea alertMessageArea;

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
    private TableView<PublicAlert> publicAlertTableView;

    @FXML
    private TableView<EvacuationShelter> shelterTableView;

    @FXML
    private TableView<User> userManagementTableView;

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

        setupComboBoxes();
        setupTables();
        setupEvacuationShelterFeature();
        setupPublicAlertFeature();
        setupUserManagementFeature();
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
     * Sets up the evacuation shelter feature.
     */
    private void setupEvacuationShelterFeature() {
        shelterStatusComboBox.getItems().setAll(
                "AVAILABLE",
                "NEAR_CAPACITY",
                "FULL",
                "CLOSED"
        );

        shelterTableView.setItems(evacuationShelters);
        setupShelterTable();
        loadEvacuationSheltersFromBackend();
        prepareShelterId();
        refreshShelterCounters();
        setShelterAddMode();

    }

    /**
     * Loads evacuation shelters from the backend server.
     */
    private void loadEvacuationSheltersFromBackend() {
        ClientResponse response = backendClient.getEvacuationShelters();

        if (!response.isSuccess()) {
            shelterStatusLabel.setText(response.getMessage());
            return;
        }

        evacuationShelters.clear();

        for (Map<String, String> row : response.getDataList()) {
            evacuationShelters.add(convertMapToEvacuationShelter(row));
        }

        shelterTableView.refresh();
        refreshShelterCounters();
    }

    /**
     * Sets up evacuation shelter table columns.
     */
    private void setupShelterTable() {
        shelterTableView.getColumns().get(0)
                .setCellValueFactory(new PropertyValueFactory<>("shelterId"));
        shelterTableView.getColumns().get(1)
                .setCellValueFactory(new PropertyValueFactory<>("shelterName"));
        shelterTableView.getColumns().get(2)
                .setCellValueFactory(new PropertyValueFactory<>("location"));
        shelterTableView.getColumns().get(3)
                .setCellValueFactory(new PropertyValueFactory<>("totalCapacity"));
        shelterTableView.getColumns().get(4)
                .setCellValueFactory(new PropertyValueFactory<>("currentOccupants"));
        shelterTableView.getColumns().get(5)
                .setCellValueFactory(new PropertyValueFactory<>("availableSpaces"));
        shelterTableView.getColumns().get(6)
                .setCellValueFactory(new PropertyValueFactory<>("shelterStatus"));
        shelterTableView.getColumns().get(7)
                .setCellValueFactory(new PropertyValueFactory<>("lastUpdated"));

        shelterTableView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, shelter) -> {
                    if (shelter != null) {
                        showSelectedShelter(shelter);
                        setShelterEditMode();
                    } else {
                        setShelterAddMode();
                    }
                });
    }

    /**
     * Converts backend shelter data into an EvacuationShelter object.
     *
     * @param row backend data row
     * @return evacuation shelter object
     */
    private EvacuationShelter convertMapToEvacuationShelter(
            Map<String, String> row) {

        int totalCapacity = parseInteger(safeMapValue(row, "totalCapacity"));
        int currentOccupants = parseInteger(
                safeMapValue(row, "currentOccupants"));

        return new EvacuationShelter(
                safeMapValue(row, "shelterId"),
                safeMapValue(row, "shelterName"),
                safeMapValue(row, "location"),
                totalCapacity,
                currentOccupants,
                safeMapValue(row, "shelterStatus"),
                safeMapValue(row, "lastUpdated")
        );
    }

    /**
     * Sets the shelter form to add mode.
     */
    private void setShelterAddMode() {
        addShelterButton.setDisable(false);
        updateShelterButton.setDisable(true);
    }

    /**
     * Sets the shelter form to edit mode.
     */
    private void setShelterEditMode() {
        addShelterButton.setDisable(true);
        updateShelterButton.setDisable(false);
    }

    /**
     * Sets up the public alert feature.
     */
    private void setupPublicAlertFeature() {
        alertTypeComboBox.getItems().setAll(
                "Evacuation Warning",
                "Fire Alert",
                "Flood Warning",
                "Road Closure",
                "Shelter Update"
        );

        alertSeverityComboBox.getItems().setAll(
                "LOW",
                "MEDIUM",
                "HIGH",
                "CRITICAL"
        );

        alertStatusComboBox.getItems().setAll(
                "DRAFT",
                "PUBLISHED",
                "CANCELLED",
                "EXPIRED"
        );

        publicAlertStatusFilterComboBox.getItems().setAll(
                "ALL",
                "DRAFT",
                "PUBLISHED",
                "EXPIRED",
                "CANCELLED"
        );
        publicAlertStatusFilterComboBox.setValue("ALL");

        refreshAlertIncidentComboBox();
        publicAlertTableView.setItems(filteredPublicAlerts);
        setupPublicAlertTable();
        loadPublicAlertsFromBackend();
        refreshPublicAlertDisplay();
        prepareAlertId();
    }

    /**
     * Loads public alerts from the backend server.
     */
    private void loadPublicAlertsFromBackend() {
        ClientResponse response;

        if (UserSession.getCurrentRole() == UserRole.PUBLIC_USER) {
            response = backendClient.getPublicAlertsForPublicUser();
        } else {
            response = backendClient.getAllPublicAlerts();
        }

        if (!response.isSuccess()) {
            publicAlertStatusLabel.setText(response.getMessage());
            return;
        }

        publicAlerts.clear();

        for (Map<String, String> row : response.getDataList()) {
            publicAlerts.add(convertMapToPublicAlert(row));
        }
    }

    /**
     * Converts backend public alert data into a PublicAlert object.
     *
     * @param row backend data row
     * @return public alert object
     */
    private PublicAlert convertMapToPublicAlert(Map<String, String> row) {
        return new PublicAlert(
                safeMapValue(row, "alertId"),
                safeMapValue(row, "incidentId"),
                safeMapValue(row, "alertType"),
                safeMapValue(row, "affectedArea"),
                safeMapValue(row, "severityLevel"),
                safeMapValue(row, "alertMessage"),
                safeMapValue(row, "createdBy"),
                safeMapValue(row, "createdTime"),
                safeMapValue(row, "alertStatus")
        );
    }

    /**
     * Refreshes the public alert table based on role and filters.
     */
    private void refreshPublicAlertDisplay() {
        filteredPublicAlerts.clear();

        String keyword = "";

        if (publicAlertSearchField != null) {
            keyword = publicAlertSearchField.getText().trim().toLowerCase();
        }

        String selectedStatus = "ALL";

        if (publicAlertStatusFilterComboBox != null
                && publicAlertStatusFilterComboBox.getValue() != null) {
            selectedStatus = publicAlertStatusFilterComboBox.getValue();
        }

        boolean publicUser
                = UserSession.getCurrentRole() == UserRole.PUBLIC_USER;

        for (PublicAlert alert : publicAlerts) {
            if (publicUser
                    && !"PUBLISHED".equalsIgnoreCase(alert.getAlertStatus())) {
                continue;
            }

            if (!publicUser
                    && !"ALL".equals(selectedStatus)
                    && !selectedStatus.equalsIgnoreCase(alert.getAlertStatus())) {
                continue;
            }

            if (!matchesPublicAlertKeyword(alert, keyword)) {
                continue;
            }

            filteredPublicAlerts.add(alert);
        }

        publicAlertTableView.refresh();
    }

    /**
     * Checks whether an alert matches the keyword filter.
     *
     * @param alert public alert
     * @param keyword search keyword
     * @return true if matched
     */
    private boolean matchesPublicAlertKeyword(PublicAlert alert, String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return true;
        }

        return alert.getAlertId().toLowerCase().contains(keyword)
                || alert.getIncidentId().toLowerCase().contains(keyword)
                || alert.getAlertType().toLowerCase().contains(keyword)
                || alert.getAffectedArea().toLowerCase().contains(keyword)
                || alert.getSeverityLevel().toLowerCase().contains(keyword)
                || alert.getAlertMessage().toLowerCase().contains(keyword)
                || alert.getAlertStatus().toLowerCase().contains(keyword);
    }

    /**
     * Applies search and filter to public alerts.
     */
    @FXML
    private void handleSearchPublicAlerts() {
        loadPublicAlertsFromBackend();
        refreshPublicAlertDisplay();
    }

    /**
     * Resets public alert search and filter.
     */
    @FXML
    private void handleResetPublicAlertSearch() {
        publicAlertSearchField.clear();
        publicAlertStatusFilterComboBox.setValue("ALL");
        loadPublicAlertsFromBackend();
        refreshPublicAlertDisplay();
    }

    /**
     * Refreshes incident IDs for public alert creation.
     */
    private void refreshAlertIncidentComboBox() {
        alertIncidentIdComboBox.getItems().clear();

        for (Incident incident : ApplicationRepository.getIncidents()) {
            alertIncidentIdComboBox.getItems().add(incident.getIncidentId());
        }
    }

    /**
     * Sets up the public alert table columns.
     */
    private void setupPublicAlertTable() {
        publicAlertTableView.getColumns().get(0)
                .setCellValueFactory(new PropertyValueFactory<>("alertId"));
        publicAlertTableView.getColumns().get(1)
                .setCellValueFactory(new PropertyValueFactory<>("incidentId"));
        publicAlertTableView.getColumns().get(2)
                .setCellValueFactory(new PropertyValueFactory<>("alertType"));
        publicAlertTableView.getColumns().get(3)
                .setCellValueFactory(new PropertyValueFactory<>("affectedArea"));
        publicAlertTableView.getColumns().get(4)
                .setCellValueFactory(new PropertyValueFactory<>("severityLevel"));
        publicAlertTableView.getColumns().get(5)
                .setCellValueFactory(new PropertyValueFactory<>("alertStatus"));
        publicAlertTableView.getColumns().get(6)
                .setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        publicAlertTableView.getColumns().get(7)
                .setCellValueFactory(new PropertyValueFactory<>("createdTime"));
        publicAlertTableView.getColumns().get(8)
                .setCellValueFactory(new PropertyValueFactory<>("alertMessage"));
    }

    /**
     * Sets up the admin user management feature.
     */
    private void setupUserManagementFeature() {
        adminUserRoleComboBox.getItems().setAll(
                "PUBLIC_USER",
                "EMERGENCY_CONTROL_CENTRE",
                "SYSTEM_ADMINISTRATOR"
        );

        adminAccountStatusComboBox.getItems().setAll(
                "ACTIVE",
                "INACTIVE"
        );

        userManagementTableView.setItems(systemUsers);
        setupUserManagementTable();
        loadSystemUsersFromBackend();
        prepareSystemUserId();
    }

    /**
     * Loads system users from the backend server.
     */
    private void loadSystemUsersFromBackend() {
        ClientResponse response = backendClient.getUsers();

        if (!response.isSuccess()) {
            userManagementStatusLabel.setText(response.getMessage());
            return;
        }

        systemUsers.clear();

        for (Map<String, String> row : response.getDataList()) {
            systemUsers.add(convertMapToUser(row));
        }

        userManagementTableView.refresh();
    }

    /**
     * Sets up the user management table columns.
     */
    private void setupUserManagementTable() {
        userManagementTableView.getColumns().get(0)
                .setCellValueFactory(new PropertyValueFactory<>("userId"));
        userManagementTableView.getColumns().get(1)
                .setCellValueFactory(new PropertyValueFactory<>("fullName"));
        userManagementTableView.getColumns().get(2)
                .setCellValueFactory(new PropertyValueFactory<>("email"));
        userManagementTableView.getColumns().get(3)
                .setCellValueFactory(new PropertyValueFactory<>("username"));
        userManagementTableView.getColumns().get(4)
                .setCellValueFactory(new PropertyValueFactory<>("role"));
        userManagementTableView.getColumns().get(5)
                .setCellValueFactory(new PropertyValueFactory<>("accountStatus"));

        userManagementTableView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, user) -> {
                    if (user != null) {
                        showSelectedSystemUser(user);
                    }
                });
    }

    /**
     * Converts backend user data into a User object.
     *
     * @param row backend data row
     * @return user object
     */
    private User convertMapToUser(Map<String, String> row) {
        return new User(
                safeMapValue(row, "userId"),
                safeMapValue(row, "fullName"),
                safeMapValue(row, "email"),
                safeMapValue(row, "username"),
                safeMapValue(row, "role"),
                safeMapValue(row, "accountStatus")
        );
    }

    /**
     * Prepares the next system user ID.
     */
    private void prepareSystemUserId() {
        int nextNumber = systemUsers.size() + 1;
        adminUserIdField.setText(String.format("U%03d", nextNumber));
    }

    /**
     * Shows selected user details in the form.
     *
     * @param user selected user
     */
    private void showSelectedSystemUser(User user) {
        adminUserIdField.setText(user.getUserId());
        adminFullNameField.setText(user.getFullName());
        adminEmailField.setText(user.getEmail());
        adminUsernameField.setText(user.getUsername());
        adminUserRoleComboBox.setValue(user.getRole());
        adminAccountStatusComboBox.setValue(user.getAccountStatus());
    }

    /**
     * Prepares the next alert ID.
     */
    private void prepareAlertId() {
        int nextNumber = publicAlerts.size() + 1;
        alertIdField.setText(String.format("AL%03d", nextNumber));
    }

    /**
     * Prepares the next shelter ID.
     */
    private void prepareShelterId() {
        int nextNumber = evacuationShelters.size() + 1;
        shelterIdField.setText(String.format("SH%03d", nextNumber));
    }

    /**
     * Shows the selected shelter in the form.
     *
     * @param shelter selected shelter
     */
    private void showSelectedShelter(EvacuationShelter shelter) {
        shelterIdField.setText(shelter.getShelterId());
        shelterNameField.setText(shelter.getShelterName());
        shelterLocationField.setText(shelter.getLocation());
        shelterCapacityField.setText(String.valueOf(shelter.getTotalCapacity()));
        shelterCurrentOccupantsField.setText(
                String.valueOf(shelter.getCurrentOccupants()));
        shelterStatusComboBox.setValue(shelter.getShelterStatus());
    }

    /**
     * Refreshes shelter summary counters.
     */
    private void refreshShelterCounters() {
        int availableShelters = 0;
        int fullShelters = 0;
        int freeSpaces = 0;

        for (EvacuationShelter shelter : evacuationShelters) {
            if ("AVAILABLE".equals(shelter.getShelterStatus())
                    || "NEAR_CAPACITY".equals(shelter.getShelterStatus())) {
                availableShelters++;
            }

            if ("FULL".equals(shelter.getShelterStatus())) {
                fullShelters++;
            }

            freeSpaces += shelter.getAvailableSpaces();
        }

        totalShelterCountLabel.setText(String.valueOf(evacuationShelters.size()));
        availableShelterCountLabel.setText(String.valueOf(availableShelters));
        fullShelterCountLabel.setText(String.valueOf(fullShelters));
        freeShelterSpaceCountLabel.setText(String.valueOf(freeSpaces));
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
     * Adds a system user record.
     */
    @FXML
    private void handleAddSystemUser() {
        if (!isSystemAdministrator()) {
            globalStatusLabel.setText("System Status: Admin access required.");
            return;
        }

        if (!validateSystemUserForm()) {
            return;
        }

        User user = new User(
                adminUserIdField.getText(),
                adminFullNameField.getText().trim(),
                adminEmailField.getText().trim(),
                adminUsernameField.getText().trim(),
                adminUserRoleComboBox.getValue(),
                adminAccountStatusComboBox.getValue()
        );

        ApplicationRepository.addSystemUser(user);
        userManagementTableView.refresh();
        handleClearSystemUserForm();

        userManagementStatusLabel.setText("User added successfully.");
        globalStatusLabel.setText("System Status: User account added.");
    }

    /**
     * Updates selected system user record.
     */
    @FXML
    private void handleUpdateSystemUser() {
        if (!isSystemAdministrator()) {
            globalStatusLabel.setText("System Status: Admin access required.");
            return;
        }

        User selectedUser
                = userManagementTableView.getSelectionModel().getSelectedItem();

        if (selectedUser == null) {
            userManagementStatusLabel.setText("Select a user to update.");
            return;
        }

        if (!validateSystemUserForm()) {
            return;
        }

        selectedUser.setFullName(adminFullNameField.getText().trim());
        selectedUser.setEmail(adminEmailField.getText().trim());
        selectedUser.setUsername(adminUsernameField.getText().trim());
        selectedUser.setRole(adminUserRoleComboBox.getValue());
        selectedUser.setAccountStatus(adminAccountStatusComboBox.getValue());

        userManagementTableView.refresh();

        userManagementStatusLabel.setText("User updated successfully.");
        globalStatusLabel.setText("System Status: User account updated.");
    }

    /**
     * Clears the user management form.
     */
    @FXML
    private void handleClearSystemUserForm() {
        adminFullNameField.clear();
        adminEmailField.clear();
        adminUsernameField.clear();
        adminUserRoleComboBox.getSelectionModel().clearSelection();
        adminAccountStatusComboBox.getSelectionModel().clearSelection();
        userManagementTableView.getSelectionModel().clearSelection();
        prepareSystemUserId();
    }

    /**
     * Validates system user form input.
     *
     * @return true if valid
     */
    private boolean validateSystemUserForm() {
        if (adminFullNameField.getText().trim().isEmpty()
                || adminEmailField.getText().trim().isEmpty()
                || adminUsernameField.getText().trim().isEmpty()
                || adminUserRoleComboBox.getValue() == null
                || adminAccountStatusComboBox.getValue() == null) {

            userManagementStatusLabel.setText("Complete all user fields.");
            return false;
        }

        if (!adminEmailField.getText().trim()
                .matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            userManagementStatusLabel.setText("Enter a valid email address.");
            return false;
        }

        if (adminUsernameField.getText().trim().length() < 4) {
            userManagementStatusLabel.setText(
                    "Username must be at least 4 characters.");
            return false;
        }

        return true;
    }

    /**
     * Checks whether the current user is the system administrator.
     *
     * @return true if current user is system administrator
     */
    private boolean isSystemAdministrator() {
        return UserSession.getCurrentRole() == UserRole.SYSTEM_ADMINISTRATOR;
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
     * Shows the admin user management pane.
     */
    @FXML
    private void showUserManagement() {
        if (!isSystemAdministrator()) {
            globalStatusLabel.setText("System Status: Admin access required.");
            return;
        }

        showOnlyPane(userManagementPane);
        pageSubtitleLabel.setText("Admin User Management");
        setActiveButton(userManagementButton);
        userManagementTableView.refresh();
    }

    /**
     * Adds a new evacuation shelter record.
     */
    @FXML
    private void handleAddShelter() {
        if (!hasOperationalAccess()) {
            globalStatusLabel.setText("System Status: Access denied.");
            return;
        }

        if (!validateShelterForm()) {
            return;
        }

        int totalCapacity = Integer.parseInt(
                shelterCapacityField.getText().trim());
        int currentOccupants = Integer.parseInt(
                shelterCurrentOccupantsField.getText().trim());

        EvacuationShelter shelter = new EvacuationShelter(
                shelterIdField.getText(),
                shelterNameField.getText().trim(),
                shelterLocationField.getText().trim(),
                totalCapacity,
                currentOccupants,
                shelterStatusComboBox.getValue(),
                LocalDateTime.now().toString()
        );

        shelterAvailabilityService.updateShelterAvailability(shelter);

        ClientResponse response = backendClient.addEvacuationShelter(shelter);

        if (!response.isSuccess()) {
            shelterStatusLabel.setText(response.getMessage());
            return;
        }

        loadEvacuationSheltersFromBackend();
        handleClearShelterForm();

        shelterStatusLabel.setText(response.getMessage());
        globalStatusLabel.setText("System Status: Shelter record added.");
    }

    /**
     * Updates the selected evacuation shelter record.
     */
    @FXML
    private void handleUpdateShelter() {
        if (!hasOperationalAccess()) {
            globalStatusLabel.setText("System Status: Access denied.");
            return;
        }

        EvacuationShelter selectedShelter
                = shelterTableView.getSelectionModel().getSelectedItem();

        if (selectedShelter == null) {
            shelterStatusLabel.setText("Select a shelter to update.");
            return;
        }

        if (!validateShelterForm()) {
            return;
        }

        int totalCapacity = Integer.parseInt(
                shelterCapacityField.getText().trim());
        int currentOccupants = Integer.parseInt(
                shelterCurrentOccupantsField.getText().trim());

        EvacuationShelter updatedShelter = new EvacuationShelter(
                selectedShelter.getShelterId(),
                shelterNameField.getText().trim(),
                shelterLocationField.getText().trim(),
                totalCapacity,
                currentOccupants,
                shelterStatusComboBox.getValue(),
                LocalDateTime.now().toString()
        );

        shelterAvailabilityService.updateShelterAvailability(updatedShelter);

        ClientResponse response = backendClient.updateEvacuationShelter(
                updatedShelter);

        if (!response.isSuccess()) {
            shelterStatusLabel.setText(response.getMessage());
            return;
        }

        loadEvacuationSheltersFromBackend();
        handleClearShelterForm();

        shelterStatusLabel.setText(response.getMessage());
        globalStatusLabel.setText("System Status: Shelter record updated.");
    }

    /**
     * Clears the shelter input form.
     */
    @FXML
    private void handleClearShelterForm() {
        shelterNameField.clear();
        shelterLocationField.clear();
        shelterCapacityField.clear();
        shelterCurrentOccupantsField.clear();
        shelterStatusComboBox.getSelectionModel().clearSelection();

        shelterTableView.getSelectionModel().clearSelection();

        prepareShelterId();
        setShelterAddMode();

        shelterStatusLabel.setText("Shelter form cleared.");
    }

    /**
     * Validates evacuation shelter form input.
     *
     * @return true if valid
     */
    private boolean validateShelterForm() {
        if (shelterNameField.getText().trim().isEmpty()
                || shelterLocationField.getText().trim().isEmpty()
                || shelterCapacityField.getText().trim().isEmpty()
                || shelterCurrentOccupantsField.getText().trim().isEmpty()
                || shelterStatusComboBox.getValue() == null) {

            shelterStatusLabel.setText("Complete all shelter fields.");
            return false;
        }

        int totalCapacity;
        int currentOccupants;

        try {
            totalCapacity = Integer.parseInt(
                    shelterCapacityField.getText().trim());
            currentOccupants = Integer.parseInt(
                    shelterCurrentOccupantsField.getText().trim());
        } catch (NumberFormatException exception) {
            shelterStatusLabel.setText(
                    "Capacity and occupants must be numeric.");
            return false;
        }

        if (totalCapacity <= 0) {
            shelterStatusLabel.setText("Total capacity must be greater than zero.");
            return false;
        }

        if (currentOccupants < 0) {
            shelterStatusLabel.setText("Current occupants cannot be negative.");
            return false;
        }

        if (currentOccupants > totalCapacity) {
            shelterStatusLabel.setText(
                    "Current occupants cannot exceed total capacity.");
            return false;
        }

        return true;
    }

    /**
     * Shows the evacuation shelter availability tracker pane.
     */
    @FXML
    private void showEvacuationShelters() {
        showOnlyPane(evacuationShelterPane);
        pageSubtitleLabel.setText("Evacuation Shelter Availability Tracker");
        setActiveButton(evacuationShelterButton);
        loadEvacuationSheltersFromBackend();
        shelterTableView.refresh();
        refreshShelterCounters();
    }

    /**
     * Shows the public alert notification screen.
     */
    @FXML
    private void showPublicAlerts() {
        showOnlyPane(publicAlertPane);
        setActiveButton(publicAlertButton);
        applyPublicAlertScreenAccess();

        if (UserSession.getCurrentRole() == UserRole.PUBLIC_USER) {
            pageSubtitleLabel.setText("Public Alerts");
        } else {
            pageSubtitleLabel.setText("Public Alert Notification Manager");
        }

        refreshPublicAlertDisplay();
    }

    /**
     * Creates a new public alert.
     */
    @FXML
    private void handleCreateAlert() {
        if (!hasOperationalAccess()) {
            globalStatusLabel.setText("System Status: Access denied.");
            return;
        }

        if (!validatePublicAlertForm()) {
            return;
        }

        PublicAlert alert = new PublicAlert(
                alertIdField.getText(),
                alertIncidentIdComboBox.getValue(),
                alertTypeComboBox.getValue(),
                alertAffectedAreaField.getText().trim(),
                alertSeverityComboBox.getValue(),
                alertMessageArea.getText().trim(),
                UserSession.getCurrentRoleDisplayName(),
                LocalDateTime.now().toString(),
                alertStatusComboBox.getValue()
        );

        if (!publicAlertService.canCreateAlert(alert)) {
            publicAlertStatusLabel.setText("Public alert validation failed.");
            return;
        }

        ClientResponse response = backendClient.createPublicAlert(alert);

        if (!response.isSuccess()) {
            publicAlertStatusLabel.setText(response.getMessage());
            return;
        }

        loadPublicAlertsFromBackend();
        refreshPublicAlertDisplay();
        handleClearAlertForm();

        publicAlertStatusLabel.setText(response.getMessage());
        globalStatusLabel.setText("System Status: Public alert created.");
    }

    /**
     * Publishes the selected public alert.
     */
    @FXML
    private void handlePublishAlert() {
        if (!hasOperationalAccess()) {
            globalStatusLabel.setText("System Status: Access denied.");
            return;
        }

        PublicAlert selectedAlert
                = publicAlertTableView.getSelectionModel().getSelectedItem();

        if (selectedAlert == null) {
            publicAlertStatusLabel.setText("Select an alert to publish.");
            return;
        }

        ClientResponse response = backendClient.publishPublicAlert(
                selectedAlert.getAlertId());

        if (!response.isSuccess()) {
            publicAlertStatusLabel.setText(response.getMessage());
            return;
        }

        loadPublicAlertsFromBackend();
        refreshPublicAlertDisplay();

        publicAlertStatusLabel.setText(response.getMessage());
        globalStatusLabel.setText("System Status: Public alert published.");
    }

    /**
     * Expires the selected public alert.
     */
    @FXML
    private void handleExpireAlert() {
        if (!hasOperationalAccess()) {
            globalStatusLabel.setText("System Status: Access denied.");
            return;
        }

        PublicAlert selectedAlert
                = publicAlertTableView.getSelectionModel().getSelectedItem();

        if (selectedAlert == null) {
            publicAlertStatusLabel.setText("Select an alert to expire.");
            return;
        }

        ClientResponse response = backendClient.expirePublicAlert(
                selectedAlert.getAlertId());

        if (!response.isSuccess()) {
            publicAlertStatusLabel.setText(response.getMessage());
            return;
        }

        loadPublicAlertsFromBackend();
        refreshPublicAlertDisplay();

        publicAlertStatusLabel.setText(response.getMessage());
        globalStatusLabel.setText("System Status: Public alert expired.");
    }

    /**
     * Clears the public alert form.
     */
    @FXML
    private void handleClearAlertForm() {
        alertIncidentIdComboBox.getSelectionModel().clearSelection();
        alertTypeComboBox.getSelectionModel().clearSelection();
        alertAffectedAreaField.clear();
        alertSeverityComboBox.getSelectionModel().clearSelection();
        alertStatusComboBox.getSelectionModel().clearSelection();
        alertMessageArea.clear();
        publicAlertTableView.getSelectionModel().clearSelection();
        prepareAlertId();
    }

    /**
     * Validates public alert form input.
     *
     * @return true if valid
     */
    private boolean validatePublicAlertForm() {
        if (alertIncidentIdComboBox.getValue() == null
                || alertTypeComboBox.getValue() == null
                || alertAffectedAreaField.getText().trim().isEmpty()
                || alertSeverityComboBox.getValue() == null
                || alertStatusComboBox.getValue() == null
                || alertMessageArea.getText().trim().isEmpty()) {

            publicAlertStatusLabel.setText("Complete all public alert fields.");
            return false;
        }

        if (alertMessageArea.getText().trim().length() < 10) {
            publicAlertStatusLabel.setText(
                    "Alert message must be at least 10 characters.");
            return false;
        }

        return true;
    }

    /**
     * Applies public alert screen permissions based on user role.
     */
    private void applyPublicAlertScreenAccess() {
        boolean hasManagementAccess = hasOperationalAccess();

        publicAlertFormPane.setVisible(hasManagementAccess);
        publicAlertFormPane.setManaged(hasManagementAccess);

        createAlertButton.setVisible(hasManagementAccess);
        createAlertButton.setManaged(hasManagementAccess);
        createAlertButton.setDisable(!hasManagementAccess);

        publishAlertButton.setVisible(hasManagementAccess);
        publishAlertButton.setManaged(hasManagementAccess);
        publishAlertButton.setDisable(!hasManagementAccess);

        expireAlertButton.setVisible(hasManagementAccess);
        expireAlertButton.setManaged(hasManagementAccess);
        expireAlertButton.setDisable(!hasManagementAccess);

        clearAlertButton.setVisible(hasManagementAccess);
        clearAlertButton.setManaged(hasManagementAccess);
        clearAlertButton.setDisable(!hasManagementAccess);

        publicAlertStatusFilterComboBox.setVisible(hasManagementAccess);
        publicAlertStatusFilterComboBox.setManaged(hasManagementAccess);

        if (!hasManagementAccess) {
            publicAlertTitleLabel.setText("Public Alerts");
        } else {
            publicAlertTitleLabel.setText("Public Alert Notification Manager");
            publicAlertStatusLabel.setText("Public alert manager ready.");
        }

        refreshPublicAlertDisplay();
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
            resourceCountersPane,
            evacuationShelterPane,
            publicAlertPane,
            userManagementPane
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
            resourceCountersButton,
            evacuationShelterButton,
            publicAlertButton,
            userManagementButton
        };

        for (Button button : buttons) {
            button.getStyleClass().remove("nav-button-active");
            button.getStyleClass().remove("sub-nav-button-active");
            button.getStyleClass().remove("nav-button");
            button.getStyleClass().remove("sub-nav-button");

            if (button == dashboardButton
                    || button == publicAlertButton
                    || button == reportButton
                    || button == userManagementButton) {
                button.getStyleClass().add("nav-button");
            } else {
                button.getStyleClass().add("sub-nav-button");
            }
        }

        activeButton.getStyleClass()
                .remove("nav-button");
        activeButton.getStyleClass().remove("sub-nav-button");

        if (activeButton == dashboardButton
                || activeButton == publicAlertButton
                || activeButton == reportButton
                || activeButton == userManagementButton) {
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
        setButtonAccess(publicAlertButton, true);
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
        setButtonAccess(evacuationShelterButton, false);
        setButtonAccess(userManagementButton, false);

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
        setButtonAccess(evacuationShelterButton, true);
        setButtonAccess(publicAlertButton, true);
        setButtonAccess(userManagementButton, false);

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
        setButtonAccess(evacuationShelterButton, true);
        setButtonAccess(publicAlertButton, true);
        setButtonAccess(userManagementButton, true);

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
            showPublicAlerts();
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

    /**
     * Safely reads a value from a backend data row.
     *
     * @param row backend data row
     * @param key data key
     * @return value or empty string
     */
    private String safeMapValue(Map<String, String> row, String key) {
        if (row == null || !row.containsKey(key) || row.get(key) == null) {
            return "";
        }

        return row.get(key);
    }

    /**
     * Converts text into an integer.
     *
     * @param value text value
     * @return integer value, or zero if invalid
     */
    private int parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            return 0;
        }
    }

}
