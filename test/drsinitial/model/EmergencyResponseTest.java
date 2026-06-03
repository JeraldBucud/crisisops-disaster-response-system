package drsinitial.model;

import drsinitial.model.enums.AgencyType;
import drsinitial.model.enums.IncidentStatus;
import drsinitial.model.enums.ResourceStatus;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests emergency response dispatch behaviour for the
 * Disaster Response System prototype.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class EmergencyResponseTest {

    /**
     * TP08
     *
     * Tests that an emergency response is dispatched successfully
     * when the agency is available and the resource has available units.
     */
    @Test
    public void testDispatchResponseSuccess() {

        Incident incident = new Incident(
                "I012",
                "R012",
                30,
                "Brisbane CBD"
        );

        ResponseAgency agency = new ResponseAgency(
                "A001",
                "Fire and Emergency Services",
                "000",
                AgencyType.FIRE_RESPONSE
        );

        EmergencyResource resource = new EmergencyResource(
                "RES001",
                "Fire Truck",
                "Fire Response",
                3
        );

        EmergencyResponse response = new EmergencyResponse(
                "ER001",
                incident,
                agency,
                resource,
                "Fire truck dispatched to incident location."
        );

        boolean result = response.dispatchResponse();

        assertTrue(result);
        assertEquals(IncidentStatus.DISPATCHED,
                response.getResponseStatus());
        assertEquals(IncidentStatus.DISPATCHED,
                incident.getIncidentStatus());
        assertEquals(2, resource.getAvailableQuantity());
        assertEquals(1, resource.getAssignedQuantity());
        assertNotNull(response.getDispatchDateTime());
    }

    /**
     * TP25
     *
     * Tests that dispatch is rejected when the selected
     * resource has no available units.
     */
    @Test
    public void testDispatchResponseRejectedWhenResourceUnavailable() {

        Incident incident = new Incident(
                "I013",
                "R013",
                12,
                "South Brisbane"
        );

        ResponseAgency agency = new ResponseAgency(
                "A002",
                "Security Response Team",
                "000",
                AgencyType.SECURITY_RESPONSE
        );

        EmergencyResource resource = new EmergencyResource(
                "RES002",
                "Security Unit",
                "Security Response",
                2
        );

        resource.setResourceStatus(ResourceStatus.ASSIGNED);

        EmergencyResponse response = new EmergencyResponse(
                "ER002",
                incident,
                agency,
                resource,
                "Attempt dispatch with no available security units."
        );

        boolean result = response.dispatchResponse();

        assertFalse(result);
        assertEquals(IncidentStatus.DISPATCHED,
                response.getResponseStatus());
        assertEquals(IncidentStatus.REGISTERED,
                incident.getIncidentStatus());
        assertEquals(0, resource.getAvailableQuantity());
        assertEquals(2, resource.getAssignedQuantity());
    }
}