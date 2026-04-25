package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete report implementation for a course.
 * Provides course-specific data and formatting.
 *
 * @author Aayush Bashyal
 */
public class CourseReport extends Report {

    private String courseName;
    private int enrollment;

    /**
     * Loads course data into the report fields.
     */
    @Override
    protected void loadData() {
        courseName = "CSCI 363";
        enrollment = 45;
    }

    /**
     * Returns the course report header.
     *
     * @return header string
     */
    @Override
    protected String formatHeader() {
        return "Course Report";
    }

    /**
     * Returns the course report body containing course name and enrollment.
     *
     * @return body string
     */
    @Override
    protected String formatBody() {
        return "Course: " + courseName + "\nEnrollment: " + enrollment;
    }

    /**
     * Returns the course report footer.
     *
     * @return footer string
     */
    @Override
    protected String formatFooter() {
        return "End of Course Report";
    }
}
