package drsinitial.model;

/**
 * Represents an evacuation shelter used during disaster response.
 *
 * This model supports the Evacuation Shelter Availability Tracker
 * feature in DRS-Enhanced.
 *
 * @author Jerald Christopher Bucud
 */
public class EvacuationShelter {

    private String shelterId;
    private String shelterName;
    private String location;
    private int totalCapacity;
    private int currentOccupants;
    private int availableSpaces;
    private String shelterStatus;
    private String lastUpdated;

    /**
     * Creates an evacuation shelter record.
     *
     * @param shelterId shelter ID
     * @param shelterName shelter name
     * @param location shelter location
     * @param totalCapacity total capacity
     * @param currentOccupants current occupants
     * @param shelterStatus shelter status
     * @param lastUpdated last updated date and time
     */
    public EvacuationShelter(String shelterId,
            String shelterName,
            String location,
            int totalCapacity,
            int currentOccupants,
            String shelterStatus,
            String lastUpdated) {

        this.shelterId = shelterId;
        this.shelterName = shelterName;
        this.location = location;
        this.totalCapacity = totalCapacity;
        this.currentOccupants = currentOccupants;
        this.availableSpaces = totalCapacity - currentOccupants;
        this.shelterStatus = shelterStatus;
        this.lastUpdated = lastUpdated;
    }

    public String getShelterId() {
        return this.shelterId;
    }

    public void setShelterId(String shelterId) {
        this.shelterId = shelterId;
    }

    public String getShelterName() {
        return this.shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalCapacity() {
        return this.totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
        updateAvailableSpaces();
    }

    public int getCurrentOccupants() {
        return this.currentOccupants;
    }

    public void setCurrentOccupants(int currentOccupants) {
        this.currentOccupants = currentOccupants;
        updateAvailableSpaces();
    }

    public int getAvailableSpaces() {
        return this.availableSpaces;
    }

    public void setAvailableSpaces(int availableSpaces) {
        this.availableSpaces = availableSpaces;
    }

    public String getShelterStatus() {
        return this.shelterStatus;
    }

    public void setShelterStatus(String shelterStatus) {
        this.shelterStatus = shelterStatus;
    }

    public String getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * Updates available spaces using capacity and occupants.
     */
    private void updateAvailableSpaces() {
        this.availableSpaces = this.totalCapacity - this.currentOccupants;
    }

    /**
     * Returns a readable shelter record.
     *
     * @return shelter display text
     */
    @Override
    public String toString() {
        return this.shelterId + " - " + this.shelterName;
    }
}