package drsinitial.model;

import drsinitial.model.enums.DisasterType;
import drsinitial.model.enums.IncidentStatus;
import drsinitial.model.enums.SeverityLevel;
import java.time.LocalDateTime;

/**
 * Represents a disaster report submitted to the
 * Disaster Response System.
 *
 * This class stores disaster report details including:
 * - reporter information
 * - disaster type
 * - location
 * - severity level
 * - report status
 *
 * @author Jerald Christopher Bucud
 */
public class DisasterReport {

    /**
     * Unique disaster report identifier.
     */
    private String reportId;

    /**
     * Name of the person submitting the report.
     */
    private String reporterName;

    /**
     * Type of disaster being reported.
     */
    private DisasterType disasterType;

    /**
     * Disaster location.
     */
    private String location;

    /**
     * Detailed disaster description.
     */
    private String description;

    /**
     * Date and time when the report was created.
     */
    private LocalDateTime dateTime;

    /**
     * Initial severity level of the disaster.
     */
    private SeverityLevel initialSeverity;

    /**
     * Current report status.
     */
    private IncidentStatus reportStatus;

    /**
     * Creates a default disaster report.
     *
     * The default report status is REPORTED.
     */
    public DisasterReport() {
        this.dateTime = LocalDateTime.now();
        this.reportStatus = IncidentStatus.REPORTED;
    }

    /**
     * Creates a disaster report with complete details.
     *
     * @param reportId unique report identifier
     * @param reporterName reporter name
     * @param disasterType disaster category
     * @param location disaster location
     * @param description disaster description
     * @param initialSeverity initial severity level
     */
    public DisasterReport(String reportId,
            String reporterName,
            DisasterType disasterType,
            String location,
            String description,
            SeverityLevel initialSeverity) {

        this.reportId = reportId;
        this.reporterName = reporterName;
        this.disasterType = disasterType;
        this.location = location;
        this.description = description;
        this.initialSeverity = initialSeverity;
        this.dateTime = LocalDateTime.now();
        this.reportStatus = IncidentStatus.REPORTED;
    }

    /**
     * Validates the disaster report fields.
     *
     * @return true if all required fields are valid,
     * otherwise false
     */
    public boolean validateReport() {

        return this.reportId != null
                && !this.reportId.isBlank()
                && this.reporterName != null
                && !this.reporterName.isBlank()
                && this.disasterType != null
                && this.location != null
                && !this.location.isBlank()
                && this.description != null
                && !this.description.isBlank()
                && this.initialSeverity != null;
    }

    /**
     * Updates the report status.
     *
     * @param reportStatus updated incident status
     */
    public void updateStatus(IncidentStatus reportStatus) {
        this.reportStatus = reportStatus;
    }

    /**
     * Returns the report identifier.
     *
     * @return report identifier
     */
    public String getReportId() {
        return this.reportId;
    }

    /**
     * Updates the report identifier.
     *
     * @param reportId report identifier
     */
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    /**
     * Returns the reporter name.
     *
     * @return reporter name
     */
    public String getReporterName() {
        return this.reporterName;
    }

    /**
     * Updates the reporter name.
     *
     * @param reporterName reporter name
     */
    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    /**
     * Returns the disaster type.
     *
     * @return disaster type
     */
    public DisasterType getDisasterType() {
        return this.disasterType;
    }

    /**
     * Updates the disaster type.
     *
     * @param disasterType disaster category
     */
    public void setDisasterType(DisasterType disasterType) {
        this.disasterType = disasterType;
    }

    /**
     * Returns the disaster location.
     *
     * @return disaster location
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Updates the disaster location.
     *
     * @param location disaster location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns the disaster description.
     *
     * @return disaster description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Updates the disaster description.
     *
     * @param description disaster description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the report date and time.
     *
     * @return report date and time
     */
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Updates the report date and time.
     *
     * @param dateTime report date and time
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns the initial severity level.
     *
     * @return initial severity level
     */
    public SeverityLevel getInitialSeverity() {
        return this.initialSeverity;
    }

    /**
     * Updates the initial severity level.
     *
     * @param initialSeverity severity level
     */
    public void setInitialSeverity(SeverityLevel initialSeverity) {
        this.initialSeverity = initialSeverity;
    }

    /**
     * Returns the report status.
     *
     * @return report status
     */
    public IncidentStatus getReportStatus() {
        return this.reportStatus;
    }

    /**
     * Updates the report status.
     *
     * @param reportStatus updated status
     */
    public void setReportStatus(IncidentStatus reportStatus) {
        this.reportStatus = reportStatus;
    }

    /**
     * Returns the formatted disaster report summary.
     *
     * @return formatted report string
     */
    @Override
    public String toString() {

        return this.reportId
                + " - "
                + this.disasterType
                + " at "
                + this.location
                + " ("
                + this.reportStatus
                + ")";
    }
}