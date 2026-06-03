package drsinitial.service;

import drsinitial.model.Incident;
import drsinitial.model.enums.PriorityLevel;
import drsinitial.model.enums.SeverityLevel;

/**
 * Provides automatic priority recommendation logic
 * for disaster incidents.
 *
 * This service analyses incident severity and affected
 * population size to calculate a risk score and recommend
 * an appropriate response priority level.
 *
 * @author Jerald Christopher Bucud
 * @studentId 12301099
 * @course COIT20258 Software Engineering
 */
public class PriorityRecommendationService {

    private static final double LOW_THRESHOLD = 50;
    private static final double MEDIUM_THRESHOLD = 150;
    private static final double HIGH_THRESHOLD = 400;

    /**
     * Recommends a priority level for an incident.
     *
     * The recommendation is based on the calculated risk score.
     *
     * @param incident the incident to analyse
     * @return the recommended priority level
     */
    public PriorityLevel recommendPriority(Incident incident) {

        double riskScore = calculateRiskScore(incident);

        if (riskScore >= HIGH_THRESHOLD) {
            return PriorityLevel.EMERGENCY;
        }

        if (riskScore >= MEDIUM_THRESHOLD) {
            return PriorityLevel.HIGH;
        }

        if (riskScore >= LOW_THRESHOLD) {
            return PriorityLevel.MEDIUM;
        }

        return PriorityLevel.LOW;
    }

    /**
     * Calculates a numerical risk score for an incident.
     *
     * Formula:
     * risk score = severity weight x affected people
     *
     * Severity weights:
     * LOW = 1
     * MEDIUM = 2
     * HIGH = 3
     * CRITICAL = 4
     *
     * @param incident the incident to analyse
     * @return calculated risk score
     */
    public double calculateRiskScore(Incident incident) {

        if (incident == null || incident.getSeverityLevel() == null) {
            return 0;
        }

        return getSeverityWeight(incident.getSeverityLevel())
                * incident.getAffectedPeople();
    }

    /**
     * Converts a severity level into a numerical weight.
     *
     * @param severityLevel the severity level to convert
     * @return numerical severity weight
     */
    private double getSeverityWeight(SeverityLevel severityLevel) {

        switch (severityLevel) {
            case LOW:
                return 1;
            case MEDIUM:
                return 2;
            case HIGH:
                return 3;
            case CRITICAL:
                return 4;
            default:
                return 0;
        }
    }
}