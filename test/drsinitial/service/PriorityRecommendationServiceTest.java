package drsinitial.service;

import drsinitial.model.Incident;
import drsinitial.model.enums.PriorityLevel;
import drsinitial.model.enums.SeverityLevel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests priority recommendation behaviour for the
 * Disaster Response System prototype.
 *
 * @author Jerald Christopher Bucud
 */
public class PriorityRecommendationServiceTest {

    /**
     * TP06
     *
     * Tests that the system recommends EMERGENCY priority
     * when the calculated risk score reaches the high threshold.
     */
    @Test
    public void testRecommendEmergencyPriority() {

        PriorityRecommendationService service =
                new PriorityRecommendationService();

        Incident incident = new Incident(
                "I009",
                "R009",
                120,
                "Brisbane CBD"
        );

        incident.assessSeverity(SeverityLevel.CRITICAL);

        PriorityLevel result = service.recommendPriority(incident);

        assertEquals(PriorityLevel.EMERGENCY, result);
    }

    /**
     * TP14
     *
     * Tests that the risk score is calculated using:
     * severity weight x affected people.
     */
    @Test
    public void testCalculateRiskScore() {

        PriorityRecommendationService service =
                new PriorityRecommendationService();

        Incident incident = new Incident(
                "I010",
                "R010",
                50,
                "South Brisbane"
        );

        incident.assessSeverity(SeverityLevel.HIGH);

        double result = service.calculateRiskScore(incident);

        assertEquals(150.0, result, 0.001);
    }

    /**
     * Tests that a null incident returns a zero risk score.
     */
    @Test
    public void testCalculateRiskScoreWithNullIncident() {

        PriorityRecommendationService service =
                new PriorityRecommendationService();

        double result = service.calculateRiskScore(null);

        assertEquals(0.0, result, 0.001);
    }

    /**
     * Tests that an incident with no severity returns a zero risk score.
     */
    @Test
    public void testCalculateRiskScoreWithNoSeverity() {

        PriorityRecommendationService service =
                new PriorityRecommendationService();

        Incident incident = new Incident(
                "I011",
                "R011",
                30,
                "Fortitude Valley"
        );

        double result = service.calculateRiskScore(incident);

        assertEquals(0.0, result, 0.001);
    }
}