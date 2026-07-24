package drsinitial.model;

import drsinitial.model.enums.ResourceStatus;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests emergency resource quantity and availability behaviour
 * for the Disaster Response System prototype.
 *
 * @author Jerald Christopher Bucud
 */
public class EmergencyResourceTest {

    /**
     * TP09
     *
     * Tests that a resource with available units returns true
     * when availability is checked.
     */
    @Test
    public void testCheckAvailabilityWithAvailableUnits() {

        EmergencyResource resource = new EmergencyResource(
                "RES001",
                "Ambulance Unit",
                "Medical",
                4
        );

        boolean result = resource.checkAvailability();

        assertTrue(result);
        assertEquals(4, resource.getTotalQuantity());
        assertEquals(4, resource.getAvailableQuantity());
        assertEquals(0, resource.getAssignedQuantity());
        assertEquals(0, resource.getUnavailableQuantity());
        assertEquals(0, resource.getMaintenanceQuantity());
        assertEquals(ResourceStatus.AVAILABLE,
                resource.getResourceStatus());
    }

    /**
     * TP26
     *
     * Tests that assigning one resource unit decreases
     * available quantity and increases assigned quantity.
     */
    @Test
    public void testAssignResourceUnit() {

        EmergencyResource resource = new EmergencyResource(
                "RES002",
                "Fire Truck",
                "Fire Response",
                3
        );

        boolean result = resource.assignResource();

        assertTrue(result);
        assertEquals(3, resource.getTotalQuantity());
        assertEquals(2, resource.getAvailableQuantity());
        assertEquals(1, resource.getAssignedQuantity());
        assertEquals(0, resource.getUnavailableQuantity());
        assertEquals(0, resource.getMaintenanceQuantity());
        assertEquals(ResourceStatus.AVAILABLE,
                resource.getResourceStatus());
    }

    /**
     * TP27
     *
     * Tests that releasing one assigned resource unit decreases
     * assigned quantity and increases available quantity.
     */
    @Test
    public void testReleaseAssignedResourceUnit() {

        EmergencyResource resource = new EmergencyResource(
                "RES003",
                "Rescue Team",
                "Search and Rescue",
                5
        );

        resource.assignResource();

        boolean result = resource.releaseResource();

        assertTrue(result);
        assertEquals(5, resource.getTotalQuantity());
        assertEquals(5, resource.getAvailableQuantity());
        assertEquals(0, resource.getAssignedQuantity());
        assertEquals(0, resource.getUnavailableQuantity());
        assertEquals(0, resource.getMaintenanceQuantity());
        assertEquals(ResourceStatus.AVAILABLE,
                resource.getResourceStatus());
    }

    /**
     * Tests that assigning a resource fails when no units
     * are available.
     */
    @Test
    public void testAssignResourceFailsWhenNoUnitsAvailable() {

        EmergencyResource resource = new EmergencyResource(
                "RES004",
                "Police Unit",
                "Security",
                2
        );

        resource.setResourceStatus(ResourceStatus.ASSIGNED);

        boolean result = resource.assignResource();

        assertFalse(result);
        assertEquals(2, resource.getTotalQuantity());
        assertEquals(0, resource.getAvailableQuantity());
        assertEquals(2, resource.getAssignedQuantity());
        assertEquals(0, resource.getUnavailableQuantity());
        assertEquals(0, resource.getMaintenanceQuantity());
        assertEquals(ResourceStatus.ASSIGNED,
                resource.getResourceStatus());
    }

    /**
     * Tests that marking one available unit as unavailable works.
     */
    @Test
    public void testMarkOneUnavailable() {

        EmergencyResource resource = new EmergencyResource(
                "RES005",
                "Ambulance Unit",
                "Medical",
                4
        );

        boolean result = resource.markOneUnavailable();

        assertTrue(result);
        assertEquals(4, resource.getTotalQuantity());
        assertEquals(3, resource.getAvailableQuantity());
        assertEquals(0, resource.getAssignedQuantity());
        assertEquals(1, resource.getUnavailableQuantity());
        assertEquals(0, resource.getMaintenanceQuantity());
        assertEquals(ResourceStatus.AVAILABLE,
                resource.getResourceStatus());
    }

    /**
     * Tests that marking one available unit as maintenance works.
     */
    @Test
    public void testMarkOneMaintenance() {

        EmergencyResource resource = new EmergencyResource(
                "RES006",
                "Fire Truck",
                "Fire Response",
                3
        );

        boolean result = resource.markOneMaintenance();

        assertTrue(result);
        assertEquals(3, resource.getTotalQuantity());
        assertEquals(2, resource.getAvailableQuantity());
        assertEquals(0, resource.getAssignedQuantity());
        assertEquals(0, resource.getUnavailableQuantity());
        assertEquals(1, resource.getMaintenanceQuantity());
        assertEquals(ResourceStatus.AVAILABLE,
                resource.getResourceStatus());
    }

    /**
     * Tests that restoring one unavailable unit works.
     */
    @Test
    public void testRestoreOneUnavailable() {

        EmergencyResource resource = new EmergencyResource(
                "RES007",
                "Rescue Team",
                "Search and Rescue",
                5
        );

        resource.markOneUnavailable();

        boolean result = resource.restoreOneUnavailable();

        assertTrue(result);
        assertEquals(5, resource.getTotalQuantity());
        assertEquals(5, resource.getAvailableQuantity());
        assertEquals(0, resource.getAssignedQuantity());
        assertEquals(0, resource.getUnavailableQuantity());
        assertEquals(0, resource.getMaintenanceQuantity());
        assertEquals(ResourceStatus.AVAILABLE,
                resource.getResourceStatus());
    }

    /**
     * Tests that restoring one maintenance unit works.
     */
    @Test
    public void testRestoreOneMaintenance() {

        EmergencyResource resource = new EmergencyResource(
                "RES008",
                "Fire Truck",
                "Fire Response",
                3
        );

        resource.markOneMaintenance();

        boolean result = resource.restoreOneMaintenance();

        assertTrue(result);
        assertEquals(3, resource.getTotalQuantity());
        assertEquals(3, resource.getAvailableQuantity());
        assertEquals(0, resource.getAssignedQuantity());
        assertEquals(0, resource.getUnavailableQuantity());
        assertEquals(0, resource.getMaintenanceQuantity());
        assertEquals(ResourceStatus.AVAILABLE,
                resource.getResourceStatus());
    }

    /**
     * Tests that setting the resource status to under maintenance
     * moves all resource units to maintenance.
     */
    @Test
    public void testSetResourceStatusUnderMaintenance() {

        EmergencyResource resource = new EmergencyResource(
                "RES009",
                "Fire Truck",
                "Fire Response",
                3
        );

        resource.setResourceStatus(ResourceStatus.UNDER_MAINTENANCE);

        assertEquals(3, resource.getTotalQuantity());
        assertEquals(0, resource.getAvailableQuantity());
        assertEquals(0, resource.getAssignedQuantity());
        assertEquals(0, resource.getUnavailableQuantity());
        assertEquals(3, resource.getMaintenanceQuantity());
        assertEquals(ResourceStatus.UNDER_MAINTENANCE,
                resource.getResourceStatus());
    }
}