package drsinitial.service;

import drsinitial.model.Incident;
import drsinitial.model.enums.IncidentStatus;
import drsinitial.model.enums.PriorityLevel;
import drsinitial.model.enums.SeverityLevel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests incident service behaviour for the
 * Disaster Response System prototype.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class IncidentServiceTest {

    private IncidentService incidentService;

    /**
     * Prepares a clean incident service before each test.
     */
    @Before
    public void setUp() {
        incidentService = new IncidentService();
        incidentService.clearIncidents();
    }

    /**
     * TP03
     *
     * Tests that an incident is registered successfully
     * from a linked disaster report.
     */
    @Test
    public void testRegisterIncident() {

        Incident incident = incidentService.registerIncident(
                "I001",
                "R001",
                25,
                "Brisbane CBD"
        );

        assertNotNull(incident);
        assertEquals("I001", incident.getIncidentId());
        assertEquals("R001", incident.getReportId());
        assertEquals(25, incident.getAffectedPeople());
        assertEquals("Brisbane CBD", incident.getAffectedArea());
        assertEquals(IncidentStatus.REGISTERED,
                incident.getIncidentStatus());
        assertEquals(1, incidentService.getTotalIncidentCount());
    }

    /**
     * TP04
     *
     * Tests that an incident severity level is assessed
     * and the incident status becomes ASSESSED.
     */
    @Test
    public void testAssessIncidentSeverity() {

        Incident incident = new Incident(
                "I002",
                "R002",
                40,
                "South Brisbane"
        );

        boolean result = incidentService.assessIncident(
                incident,
                SeverityLevel.HIGH
        );

        assertTrue(result);
        assertEquals(SeverityLevel.HIGH, incident.getSeverityLevel());
        assertEquals(IncidentStatus.ASSESSED,
                incident.getIncidentStatus());
    }

    /**
     * TP05
     *
     * Tests that an incident priority level is updated
     * and the incident status becomes PRIORITISED.
     */
    @Test
    public void testPrioritiseIncident() {

        Incident incident = new Incident(
                "I003",
                "R003",
                60,
                "Fortitude Valley"
        );

        boolean result = incidentService.prioritiseIncident(
                incident,
                PriorityLevel.EMERGENCY
        );

        assertTrue(result);
        assertEquals(PriorityLevel.EMERGENCY,
                incident.getPriorityLevel());
        assertEquals(IncidentStatus.PRIORITISED,
                incident.getIncidentStatus());
    }

    /**
     * TP07
     *
     * Tests that active incidents exclude RESOLVED,
     * CLOSED, and REJECTED incidents.
     */
    @Test
    public void testGetActiveIncidentsExcludesInactiveStatuses() {

        Incident registeredIncident = new Incident(
                "I004",
                "R004",
                10,
                "Brisbane CBD"
        );

        Incident resolvedIncident = new Incident(
                "I005",
                "R005",
                5,
                "Kangaroo Point"
        );

        Incident closedIncident = new Incident(
                "I006",
                "R006",
                8,
                "New Farm"
        );

        Incident rejectedIncident = new Incident(
                "I007",
                "R007",
                2,
                "Toowong"
        );

        resolvedIncident.updateStatus(IncidentStatus.RESOLVED);
        closedIncident.updateStatus(IncidentStatus.CLOSED);
        rejectedIncident.updateStatus(IncidentStatus.REJECTED);

        incidentService.addIncident(registeredIncident);
        incidentService.addIncident(resolvedIncident);
        incidentService.addIncident(closedIncident);
        incidentService.addIncident(rejectedIncident);

        assertEquals(4, incidentService.getTotalIncidentCount());
        assertEquals(1, incidentService.getActiveIncidentCount());
        assertTrue(incidentService.getActiveIncidents()
                .contains(registeredIncident));
        assertFalse(incidentService.getActiveIncidents()
                .contains(resolvedIncident));
        assertFalse(incidentService.getActiveIncidents()
                .contains(closedIncident));
        assertFalse(incidentService.getActiveIncidents()
                .contains(rejectedIncident));
    }

    /**
     * TP10
     *
     * Tests that an incident status is updated successfully.
     */
    @Test
    public void testUpdateIncidentStatus() {

        Incident incident = new Incident(
                "I008",
                "R008",
                15,
                "West End"
        );

        boolean result = incidentService.updateIncidentStatus(
                incident,
                IncidentStatus.RESOLVED
        );

        assertTrue(result);
        assertEquals(IncidentStatus.RESOLVED,
                incident.getIncidentStatus());
    }
}