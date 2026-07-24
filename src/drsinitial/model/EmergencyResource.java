package drsinitial.model;

import drsinitial.model.enums.ResourceStatus;

/**
 * Represents an emergency resource used in the
 * Disaster Response System.
 *
 * This class tracks resource availability by quantity.
 * Each resource type may have available, assigned,
 * unavailable, and maintenance units at the same time.
 *
 * @author Jerald Christopher Bucud
 */
public class EmergencyResource {

    private String resourceId;
    private String resourceName;
    private String resourceType;
    private int totalQuantity;
    private int availableQuantity;
    private int assignedQuantity;
    private int unavailableQuantity;
    private int maintenanceQuantity;

    /**
     * Creates a default emergency resource.
     */
    public EmergencyResource() {

    }

    /**
     * Creates an emergency resource with complete details.
     *
     * All resource units are available by default.
     *
     * @param resourceId unique resource identifier
     * @param resourceName resource name
     * @param resourceType resource category
     * @param totalQuantity total number of units
     */
    public EmergencyResource(String resourceId,
            String resourceName,
            String resourceType,
            int totalQuantity) {

        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.totalQuantity = Math.max(totalQuantity, 0);
        this.availableQuantity = this.totalQuantity;
        this.assignedQuantity = 0;
        this.unavailableQuantity = 0;
        this.maintenanceQuantity = 0;
    }

    /**
     * Checks whether at least one resource unit is available.
     *
     * @return true if one or more units are available
     */
    public boolean checkAvailability() {
        return this.availableQuantity > 0;
    }

    /**
     * Assigns one available unit.
     *
     * @return true if one unit was assigned
     */
    public boolean assignResource() {

        if (this.availableQuantity <= 0) {
            return false;
        }

        this.availableQuantity--;
        this.assignedQuantity++;
        return true;
    }

    /**
     * Releases one assigned unit back to available.
     *
     * @return true if one unit was released
     */
    public boolean releaseResource() {

        if (this.assignedQuantity <= 0) {
            return false;
        }

        this.assignedQuantity--;
        this.availableQuantity++;
        return true;
    }

    /**
     * Marks one available unit as unavailable.
     *
     * @return true if one unit was marked unavailable
     */
    public boolean markOneUnavailable() {

        if (this.availableQuantity <= 0) {
            return false;
        }

        this.availableQuantity--;
        this.unavailableQuantity++;
        return true;
    }

    /**
     * Marks one available unit as under maintenance.
     *
     * @return true if one unit was marked under maintenance
     */
    public boolean markOneMaintenance() {

        if (this.availableQuantity <= 0) {
            return false;
        }

        this.availableQuantity--;
        this.maintenanceQuantity++;
        return true;
    }

    /**
     * Restores one unavailable unit back to available.
     *
     * @return true if one unavailable unit was restored
     */
    public boolean restoreOneUnavailable() {

        if (this.unavailableQuantity <= 0) {
            return false;
        }

        this.unavailableQuantity--;
        this.availableQuantity++;
        return true;
    }

    /**
     * Restores one maintenance unit back to available.
     *
     * @return true if one maintenance unit was restored
     */
    public boolean restoreOneMaintenance() {

        if (this.maintenanceQuantity <= 0) {
            return false;
        }

        this.maintenanceQuantity--;
        this.availableQuantity++;
        return true;
    }

    /**
     * Returns the summary resource status based on quantities.
     *
     * @return current summary resource status
     */
    public ResourceStatus getResourceStatus() {

        if (this.availableQuantity > 0) {
            return ResourceStatus.AVAILABLE;
        }

        if (this.assignedQuantity > 0) {
            return ResourceStatus.ASSIGNED;
        }

        if (this.unavailableQuantity > 0) {
            return ResourceStatus.UNAVAILABLE;
        }

        return ResourceStatus.UNDER_MAINTENANCE;
    }

    /**
     * Updates all units to the selected resource status.
     *
     * This supports existing sample data setup.
     *
     * @param resourceStatus selected resource status
     */
    public void setResourceStatus(ResourceStatus resourceStatus) {

        if (resourceStatus == null) {
            return;
        }

        this.availableQuantity = 0;
        this.assignedQuantity = 0;
        this.unavailableQuantity = 0;
        this.maintenanceQuantity = 0;

        if (resourceStatus == ResourceStatus.AVAILABLE) {
            this.availableQuantity = this.totalQuantity;
        } else if (resourceStatus == ResourceStatus.ASSIGNED) {
            this.assignedQuantity = this.totalQuantity;
        } else if (resourceStatus == ResourceStatus.UNAVAILABLE) {
            this.unavailableQuantity = this.totalQuantity;
        } else if (resourceStatus == ResourceStatus.UNDER_MAINTENANCE) {
            this.maintenanceQuantity = this.totalQuantity;
        }
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return this.resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getTotalQuantity() {
        return this.totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = Math.max(totalQuantity, 0);
    }

    public int getAvailableQuantity() {
        return this.availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = Math.max(availableQuantity, 0);
    }

    public int getAssignedQuantity() {
        return this.assignedQuantity;
    }

    public void setAssignedQuantity(int assignedQuantity) {
        this.assignedQuantity = Math.max(assignedQuantity, 0);
    }

    public int getUnavailableQuantity() {
        return this.unavailableQuantity;
    }

    public void setUnavailableQuantity(int unavailableQuantity) {
        this.unavailableQuantity = Math.max(unavailableQuantity, 0);
    }

    public int getMaintenanceQuantity() {
        return this.maintenanceQuantity;
    }

    public void setMaintenanceQuantity(int maintenanceQuantity) {
        this.maintenanceQuantity = Math.max(maintenanceQuantity, 0);
    }

    @Override
    public String toString() {

        return this.resourceName
                + " - Available: "
                + this.availableQuantity
                + ", Assigned: "
                + this.assignedQuantity
                + ", Unavailable: "
                + this.unavailableQuantity
                + ", Maintenance: "
                + this.maintenanceQuantity;
    }
}