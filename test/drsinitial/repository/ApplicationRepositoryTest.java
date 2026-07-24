package drsinitial.repository;

import drsinitial.model.DisasterReport;
import drsinitial.model.enums.DisasterType;
import drsinitial.model.enums.SeverityLevel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests repository storage and retrieval behaviour for the
 * Disaster Response System prototype.
 *
 * @author Jerald Christopher Bucud
 */
public class ApplicationRepositoryTest {

    /**
     * Clears repository lists before each test.
     */
    @Before
    public void setUp() {
        ApplicationRepository.getDisasterReports().clear();
        ApplicationRepository.getIncidents().clear();
        ApplicationRepository.getIncidentUpdates().clear();
        ApplicationRepository.getEmergencyResponses().clear();
        ApplicationRepository.getEmergencyResources().clear();
        ApplicationRepository.getResponseAgencies().clear();
    }

    /**
     * TP17
     *
     * Tests that a disaster report is added to the repository
     * and can be found by report ID.
     */
    @Test
    public void testAddAndFindDisasterReport() {

        DisasterReport report = new DisasterReport(
                "R001",
                "Juan Dela Cruz",
                DisasterType.FIRE,
                "Brisbane CBD",
                "Building fire reported near Queen Street.",
                SeverityLevel.HIGH
        );

        ApplicationRepository.addDisasterReport(report);

        DisasterReport foundReport =
                ApplicationRepository.findReportById("R001");

        assertEquals(1, ApplicationRepository.getDisasterReports().size());
        assertNotNull(foundReport);
        assertEquals("R001", foundReport.getReportId());
        assertEquals("Juan Dela Cruz", foundReport.getReporterName());
        assertEquals(DisasterType.FIRE, foundReport.getDisasterType());
        assertEquals("Brisbane CBD", foundReport.getLocation());
        assertEquals(SeverityLevel.HIGH,
                foundReport.getInitialSeverity());
    }

    /**
     * Tests that report ID generation follows the repository count.
     */
    @Test
    public void testGenerateReportId() {

        String firstId = ApplicationRepository.generateReportId();

        DisasterReport report = new DisasterReport(
                firstId,
                "Maria Santos",
                DisasterType.FLOOD,
                "South Bank",
                "Flood water reported near residential streets.",
                SeverityLevel.CRITICAL
        );

        ApplicationRepository.addDisasterReport(report);

        String secondId = ApplicationRepository.generateReportId();

        assertEquals("R001", firstId);
        assertEquals("R002", secondId);
    }

    /**
     * Tests that findReportById returns null when no report matches.
     */
    @Test
    public void testFindReportByIdReturnsNullWhenMissing() {

        DisasterReport foundReport =
                ApplicationRepository.findReportById("R999");

        assertNull(foundReport);
    }
}