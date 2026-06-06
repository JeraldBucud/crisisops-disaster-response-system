package drsinitial.model;

/**
 * Represents a public alert message created for a disaster incident.
 *
 * This model supports the Public Alert Notification Manager
 * feature in DRS-Enhanced.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class PublicAlert {

    private String alertId;
    private String incidentId;
    private String alertType;
    private String affectedArea;
    private String severityLevel;
    private String alertMessage;
    private String createdBy;
    private String createdTime;
    private String alertStatus;

    /**
     * Creates a public alert record.
     *
     * @param alertId alert ID
     * @param incidentId linked incident ID
     * @param alertType alert type
     * @param affectedArea affected area
     * @param severityLevel severity level
     * @param alertMessage alert message
     * @param createdBy creator name or role
     * @param createdTime created date and time
     * @param alertStatus alert status
     */
    public PublicAlert(String alertId,
            String incidentId,
            String alertType,
            String affectedArea,
            String severityLevel,
            String alertMessage,
            String createdBy,
            String createdTime,
            String alertStatus) {

        this.alertId = alertId;
        this.incidentId = incidentId;
        this.alertType = alertType;
        this.affectedArea = affectedArea;
        this.severityLevel = severityLevel;
        this.alertMessage = alertMessage;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.alertStatus = alertStatus;
    }

    public String getAlertId() {
        return this.alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getIncidentId() {
        return this.incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getAlertType() {
        return this.alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getAffectedArea() {
        return this.affectedArea;
    }

    public void setAffectedArea(String affectedArea) {
        this.affectedArea = affectedArea;
    }

    public String getSeverityLevel() {
        return this.severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    public String getAlertMessage() {
        return this.alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getAlertStatus() {
        return this.alertStatus;
    }

    public void setAlertStatus(String alertStatus) {
        this.alertStatus = alertStatus;
    }

    /**
     * Returns a readable alert record.
     *
     * @return alert display text
     */
    @Override
    public String toString() {
        return this.alertId + " - " + this.alertType;
    }
}