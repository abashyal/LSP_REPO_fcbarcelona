package org.howard.edu.lsp.finalexam.question2;

import java.util.ArrayList;
import java.util.List;

/**
 * Driver class demonstrating polymorphism with the Template Method pattern.
 * Adds both a StudentReport and a CourseReport to a shared list and
 * calls generateReport() on each, exercising the fixed workflow uniformly.
 *
 * @author Aayush Bashyal
 */
public class Driver {

    /**
     * Entry point for the report generation demonstration.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        List<Report> reports = new ArrayList<>();
        reports.add(new StudentReport());
        reports.add(new CourseReport());

        for (Report report : reports) {
            report.generateReport();
        }
    }
}
