package org.howard.edu.lsp.finalexam.question3;

/**
 * Calculates student grade averages and letter grades.
 *
 * @author Aayush Bashyal
 */
public class GradeCalculator {

    /**
     * Returns the average of three scores.
     *
     * @param score1 first score (0–100)
     * @param score2 second score (0–100)
     * @param score3 third score (0–100)
     * @return the arithmetic average
     * @throws IllegalArgumentException if any score is outside [0, 100]
     */
    public double average(int score1, int score2, int score3) {
        validateScore(score1);
        validateScore(score2);
        validateScore(score3);
        return (score1 + score2 + score3) / 3.0;
    }

    /**
     * Returns the letter grade corresponding to the given average.
     *
     * @param average the numeric average
     * @return "A", "B", "C", "D", or "F"
     */
    public String letterGrade(double average) {
        if (average >= 90) return "A";
        else if (average >= 80) return "B";
        else if (average >= 70) return "C";
        else if (average >= 60) return "D";
        else return "F";
    }

    /**
     * Returns whether the given average is a passing grade (>= 60).
     *
     * @param average the numeric average
     * @return {@code true} if passing, {@code false} otherwise
     */
    public boolean isPassing(double average) {
        return average >= 60;
    }

    private void validateScore(int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
    }
}
