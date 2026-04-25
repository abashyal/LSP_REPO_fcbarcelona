package org.howard.edu.lsp.finalexam.question3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test class for GradeCalculator.
 * Covers normal cases, letter grades, passing status, boundary values, and exception cases.
 *
 * @author Aayush Bashyal
 */
@DisplayName("Tests for GradeCalculator")
public class GradeCalculatorTest {

    private GradeCalculator calculator;

    /** Creates a fresh GradeCalculator before each test. */
    @BeforeEach
    public void setUp() {
        calculator = new GradeCalculator();
    }

    // ========================== average() ==========================

    @Test
    @DisplayName("Test average() normal: returns correct average for three typical scores")
    public void testAverageNormal() {
        double result = calculator.average(80, 90, 70);
        assertEquals(80.0, result, 0.001);
    }

    // ======================== letterGrade() ========================

    @Test
    @DisplayName("Test letterGrade() normal: average of 95 returns 'A'")
    public void testLetterGradeA() {
        assertEquals("A", calculator.letterGrade(95.0));
    }

    @Test
    @DisplayName("Test letterGrade() normal: average of 55 returns 'F'")
    public void testLetterGradeF() {
        assertEquals("F", calculator.letterGrade(55.0));
    }

    // ========================= isPassing() =========================

    @Test
    @DisplayName("Test isPassing() normal: average of 75 is passing")
    public void testIsPassingTrue() {
        assertTrue(calculator.isPassing(75.0));
    }

    @Test
    @DisplayName("Test isPassing() normal: average of 50 is not passing")
    public void testIsPassingFalse() {
        assertFalse(calculator.isPassing(50.0));
    }

    // =================== Boundary-Value Tests ======================

    @Test
    @DisplayName("Boundary: average of exactly 60 is passing and returns 'D'")
    public void testBoundaryExactly60() {
        double avg = calculator.average(60, 60, 60);
        assertEquals(60.0, avg, 0.001);
        assertEquals("D", calculator.letterGrade(avg));
        assertTrue(calculator.isPassing(avg));
    }

    @Test
    @DisplayName("Boundary: average of exactly 90 returns 'A'")
    public void testBoundaryExactly90() {
        double avg = calculator.average(90, 90, 90);
        assertEquals(90.0, avg, 0.001);
        assertEquals("A", calculator.letterGrade(avg));
    }

    // =================== Exception Tests (assertThrows) ============

    @Test
    @DisplayName("Exception: score below 0 throws IllegalArgumentException")
    public void testAverageScoreBelowZeroThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                calculator.average(-1, 50, 50));
    }

    @Test
    @DisplayName("Exception: score above 100 throws IllegalArgumentException")
    public void testAverageScoreAbove100Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                calculator.average(50, 101, 50));
    }
}
