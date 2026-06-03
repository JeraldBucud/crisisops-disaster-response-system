package drsinitial.model;

import drsinitial.model.enums.AgencyType;
import drsinitial.model.enums.ResourceStatus;

/**
 * Represents an emergency response agency in the
 * Disaster Response System.
 *
 * This class stores agency information including:
 * - agency identification
 * - contact information
 * - agency category
 * - availability status
 *
 * Response agencies participate in emergency
 * response coordination and dispatch operations.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class ResponseAgency {

    /**
     * Unique agency identifier.
     */
    private String agencyId;

    /**
     * Agency name.
     */
    private String agencyName;

    /**
     * Agency contact number.
     */
    private String contactNumber;

    /**
     * Agency response category.
     */
    private AgencyType agencyType;

    /**
     * Current agency availability status.
     */
    private ResourceStatus availabilityStatus;

    /**
     * Creates a default response agency.
     *
     * Default status is AVAILABLE.
     */
    public ResponseAgency() {
        this.availabilityStatus = ResourceStatus.AVAILABLE;
    }

    /**
     * Creates a response agency with complete details.
     *
     * @param agencyId unique agency identifier
     * @param agencyName agency name
     * @param contactNumber agency contact number
     * @param agencyType agency category
     */
    public ResponseAgency(String agencyId,
            String agencyName,
            String contactNumber,
            AgencyType agencyType) {

        this.agencyId = agencyId;
        this.agencyName = agencyName;
        this.contactNumber = contactNumber;
        this.agencyType = agencyType;
        this.availabilityStatus = ResourceStatus.AVAILABLE;
    }

    /**
     * Checks whether the agency is available for response.
     *
     * @return true if the agency is available,
     * otherwise false
     */
    public boolean checkAvailability() {
        return this.availabilityStatus == ResourceStatus.AVAILABLE;
    }

    /**
     * Updates the agency availability status.
     *
     * @param availabilityStatus updated availability status
     */
    public void updateAvailability(ResourceStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    /**
     * Returns the agency identifier.
     *
     * @return agency identifier
     */
    public String getAgencyId() {
        return this.agencyId;
    }

    /**
     * Updates the agency identifier.
     *
     * @param agencyId agency identifier
     */
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    /**
     * Returns the agency name.
     *
     * @return agency name
     */
    public String getAgencyName() {
        return this.agencyName;
    }

    /**
     * Updates the agency name.
     *
     * @param agencyName agency name
     */
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    /**
     * Returns the agency contact number.
     *
     * @return contact number
     */
    public String getContactNumber() {
        return this.contactNumber;
    }

    /**
     * Updates the agency contact number.
     *
     * @param contactNumber contact number
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Returns the agency type.
     *
     * @return agency category
     */
    public AgencyType getAgencyType() {
        return this.agencyType;
    }

    /**
     * Updates the agency type.
     *
     * @param agencyType agency category
     */
    public void setAgencyType(AgencyType agencyType) {
        this.agencyType = agencyType;
    }

    /**
     * Returns the agency availability status.
     *
     * @return availability status
     */
    public ResourceStatus getAvailabilityStatus() {
        return this.availabilityStatus;
    }

    /**
     * Updates the agency availability status.
     *
     * @param availabilityStatus updated status
     */
    public void setAvailabilityStatus(
            ResourceStatus availabilityStatus) {

        this.availabilityStatus = availabilityStatus;
    }

    /**
     * Returns the formatted agency summary.
     *
     * @return formatted agency string
     */
    @Override
    public String toString() {

        return this.agencyName
                + " ("
                + this.agencyType
                + ")";
    }
}