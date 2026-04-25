package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete report implementation for a student.
 * Provides student-specific data and formatting.
 *
 * @author Aayush Bashyal
 */
public class StudentReport extends Report {

    private String studentName;
    private double gpa;

    /**
     * Loads student data into the report fields.
     */
    @Override
    protected void loadData() {
        studentName = "John Doe";
        gpa = 3.8;
    }

    /**
     * Returns the student report header.
     *
     * @return header string
     */
    @Override
    protected String formatHeader() {
        return "Student Report";
    }

    /**
     * Returns the student report body containing name and GPA.
     *
     * @return body string
     */
    @Override
    protected String formatBody() {
        return "Student Name: " + studentName + "\nGPA: " + gpa;
    }

    /**
     * Returns the student report footer.
     *
     * @return footer string
     */
    @Override
    protected String formatFooter() {
        return "End of Student Report";
    }
}
