package org.howard.edu.lsp.finalexam.question2;

/**
 * Abstract base class defining the Template Method pattern for report generation.
 * The fixed workflow is: loadData → formatHeader → formatBody → formatFooter → generateReport.
 * Subclasses must implement the variable steps; the overall algorithm is sealed here.
 *
 * @author Aayush Bashyal
 */
public abstract class Report {

    /**
     * Template method that defines the fixed report-generation workflow.
     * Subclasses may not override this method.
     */
    public final void generateReport() {
        loadData();
        System.out.println("=== HEADER ===");
        System.out.println(formatHeader());
        System.out.println();
        System.out.println("=== BODY ===");
        System.out.println(formatBody());
        System.out.println();
        System.out.println("=== FOOTER ===");
        System.out.println(formatFooter());
        System.out.println();
    }

    /**
     * Loads the data required for this report.
     * Subclasses must initialize their fields here.
     */
    protected abstract void loadData();

    /**
     * Returns the formatted header string for this report.
     *
     * @return header text
     */
    protected abstract String formatHeader();

    /**
     * Returns the formatted body string for this report.
     *
     * @return body text
     */
    protected abstract String formatBody();

    /**
     * Returns the formatted footer string for this report.
     *
     * @return footer text
     */
    protected abstract String formatFooter();
}
