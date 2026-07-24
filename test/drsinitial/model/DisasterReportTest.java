package drsinitial.model;

import drsinitial.model.enums.DisasterType;
import drsinitial.model.enums.IncidentStatus;
import drsinitial.model.enums.SeverityLevel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests disaster report validation behaviour for the
 * Disaster Response System prototype.
 *
 * @author Jerald Christopher Bucud
 */
public class DisasterReportTest {

    /**
     * TP01
     *
     * Tests that a complete disaster report is accepted
     * when all required fields are provided.
     */
    @Test
    public void testValidateCompleteDisasterReport() {

        DisasterReport report = new DisasterReport(
                "R004",
                "Juan",
                DisasterType.FIRE,
                "Brisbane CBD",
                "Fire reported near Queen Street.",
                SeverityLevel.HIGH
        );

        boolean result = report.validateReport();

        assertTrue(result);
        assertEquals("R004", report.getReportId());
        assertEquals("Juan", report.getReporterName());
        assertEquals(DisasterType.FIRE, report.getDisasterType());
        assertEquals("Brisbane CBD", report.getLocation());
        assertEquals("Fire reported near Queen Street.",
                report.getDescription());
        assertEquals(SeverityLevel.HIGH, report.getInitialSeverity());
        assertEquals(IncidentStatus.REPORTED, report.getReportStatus());
        assertNotNull(report.getDateTime());
    }

    /**
     * TP02
     *
     * Tests that an incomplete disaster report is rejected
     * when a required field is missing.
     */
    @Test
    public void testValidateIncompleteDisasterReport() {

        DisasterReport report = new DisasterReport(
                "R005",
                "",
                DisasterType.FIRE,
                "Brisbane CBD",
                "Fire reported near Queen Street.",
                SeverityLevel.HIGH
        );

        boolean result = report.validateReport();

        assertFalse(result);
    }
}