package drsinitial.model;

import drsinitial.model.enums.IncidentStatus;
import java.time.LocalDateTime;

/**
 * Represents an update recorded for a disaster incident.
 *
 * This class stores update details including the incident ID,
 * update notes, person or agency that submitted the update,
 * update date and time, and the updated incident status.
 *
 * @author Jerald Christopher Bucud
 */
public class IncidentUpdate {

    /**
     * Unique incident update identifier.
     */
    private String updateId;

    /**
     * Identifier of the incident linked to this update.
     */
    private String incidentId;

    /**
     * Notes describing the incident update.
     */
    private String updateNotes;

    /**
     * Name of the user or agency that submitted the update.
     */
    private String updatedBy;

    /**
     * Date and time when the update was created.
     */
    private LocalDateTime updateDateTime;

    /**
     * Updated status assigned to the incident.
     */
    private IncidentStatus updatedStatus;

    /**
     * Creates a default incident update.
     *
     * The update date and time are set to the current date and time.
     */
    public IncidentUpdate() {
        this.updateDateTime = LocalDateTime.now();
    }

    /**
     * Creates an incident update with complete details.
     *
     * The update date and time are set to the current date and time.
     *
     * @param updateId unique update identifier
     * @param incidentId linked incident identifier
     * @param updateNotes update notes or description
     * @param updatedBy user or agency that submitted the update
     * @param updatedStatus updated incident status
     */
    public IncidentUpdate(String updateId,
            String incidentId,
            String updateNotes,
            String updatedBy,
            IncidentStatus updatedStatus) {

        this.updateId = updateId;
        this.incidentId = incidentId;
        this.updateNotes = updateNotes;
        this.updatedBy = updatedBy;
        this.updatedStatus = updatedStatus;
        this.updateDateTime = LocalDateTime.now();
    }

    /**
     * Validates the incident update fields.
     *
     * @return true if all required fields are valid,
     * otherwise false
     */
    public boolean validateUpdate() {

        return this.updateId != null
                && !this.updateId.isBlank()
                && this.incidentId != null
                && !this.incidentId.isBlank()
                && this.updateNotes != null
                && !this.updateNotes.isBlank()
                && this.updatedBy != null
                && !this.updatedBy.isBlank()
                && this.updatedStatus != null;
    }

    /**
     * Returns the update identifier.
     *
     * @return update identifier
     */
    public String getUpdateId() {
        return this.updateId;
    }

    /**
     * Updates the update identifier.
     *
     * @param updateId update identifier
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    /**
     * Returns the linked incident identifier.
     *
     * @return linked incident identifier
     */
    public String getIncidentId() {
        return this.incidentId;
    }

    /**
     * Updates the linked incident identifier.
     *
     * @param incidentId linked incident identifier
     */
    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    /**
     * Returns the update notes.
     *
     * @return update notes
     */
    public String getUpdateNotes() {
        return this.updateNotes;
    }

    /**
     * Updates the update notes.
     *
     * @param updateNotes update notes
     */
    public void setUpdateNotes(String updateNotes) {
        this.updateNotes = updateNotes;
    }

    /**
     * Returns the user or agency that submitted the update.
     *
     * @return user or agency name
     */
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Updates the user or agency that submitted the update.
     *
     * @param updatedBy user or agency name
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * Returns the update date and time.
     *
     * @return update date and time
     */
    public LocalDateTime getUpdateDateTime() {
        return this.updateDateTime;
    }

    /**
     * Updates the update date and time.
     *
     * @param updateDateTime update date and time
     */
    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    /**
     * Returns the updated incident status.
     *
     * @return updated incident status
     */
    public IncidentStatus getUpdatedStatus() {
        return this.updatedStatus;
    }

    /**
     * Updates the incident status.
     *
     * @param updatedStatus updated incident status
     */
    public void setUpdatedStatus(IncidentStatus updatedStatus) {
        this.updatedStatus = updatedStatus;
    }

    /**
     * Returns the formatted incident update summary.
     *
     * @return formatted incident update string
     */
    @Override
    public String toString() {

        return this.updateId
                + " - "
                + this.incidentId
                + " - "
                + this.updatedStatus
                + " - "
                + this.updateDateTime;
    }
}