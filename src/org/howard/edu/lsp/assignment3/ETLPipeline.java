package org.howard.edu.lsp.assignment3;

import java.io.IOException;

/**
 * Orchestrates the Extract, Transform, and Load (ETL) pipeline for product data.
 *
 * <p>This class coordinates the three pipeline stages using dedicated helper classes:
 * <ul>
 *   <li><b>Extract</b> — {@link CSVReader} reads raw rows from the input file.</li>
 *   <li><b>Validate</b> — {@link ProductValidator} parses and validates each row.</li>
 *   <li><b>Transform</b> — {@link Transformer} (implemented by {@link ProductTransformer})
 *       applies all business rules.</li>
 *   <li><b>Load</b> — {@link CSVWriter} writes transformed records to the output file.</li>
 * </ul>
 * </p>
 *
 * <p>Input:  {@code data/products.csv}<br>
 * Output: {@code data/transformed_products.csv}</p>
 *
 * @author Aayush Bashyal
 * @version 1.0
 */
public class ETLPipeline {

    /** Relative path to the input CSV file. */
    private static final String INPUT_FILE = "data/products.csv";

    /** Relative path to the output CSV file. */
    private static final String OUTPUT_FILE = "data/transformed_products.csv";

    /** Number of data rows read (excluding header and blank lines). */
    private int rowsRead = 0;

    /** Number of rows successfully transformed and written. */
    private int rowsTransformed = 0;

    /** Number of rows skipped due to validation failures or blank lines. */
    private int rowsSkipped = 0;

    /**
     * Application entry point. Creates and runs the ETL pipeline.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ETLPipeline pipeline = new ETLPipeline();
        pipeline.run();
    }

    /**
     * Executes the full ETL pipeline from extraction through loading.
     *
     * <p>Opens the input file via {@link CSVReader}, validates each row using
     * {@link ProductValidator}, transforms valid products using {@link ProductTransformer},
     * and writes results via {@link CSVWriter}. Prints a run summary on completion.</p>
     *
     * <p>If the input file cannot be opened, or the output file cannot be created,
     * an error is printed and the pipeline halts gracefully.</p>
     */
    public void run() {
        CSVReader reader = new CSVReader(INPUT_FILE);
        CSVWriter writer = new CSVWriter(OUTPUT_FILE);
        ProductValidator validator = new ProductValidator();
        Transformer transformer = new ProductTransformer();

        // --- Extract: Open input file ---
        if (!reader.open()) {
            return;
        }

        // --- Load setup: Open output file ---
        if (!writer.open()) {
            reader.close();
            return;
        }

        try {
            // Always write the output header
            writer.writeHeader();

            // Only process data rows if the input file had a header
            if (reader.hasHeader()) {
                String line;
                while ((line = reader.readLine()) != null) {

                    rowsRead++;

                    // Skip blank lines
                    if (line.trim().isEmpty()) {
                        rowsSkipped++;
                        continue;
                    }

                    // --- Validate: parse row into Product ---
                    Product product = validator.validate(line);
                    if (product == null) {
                        rowsSkipped++;
                        continue;
                    }

                    // --- Transform: apply business rules ---
                    product = transformer.transform(product);

                    // --- Load: write transformed product ---
                    writer.writeProduct(product);
                    rowsTransformed++;
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading or writing files: " + e.getMessage());
        } finally {
            reader.close();
            writer.close();
        }

        printSummary();
    }

    /**
     * Prints a summary of the pipeline run to standard output.
     *
     * <p>Reports the number of rows read, transformed, and skipped,
     * as well as the path of the output file.</p>
     */
    private void printSummary() {
        System.out.println("=== ETL Pipeline Run Summary ===");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output file: " + OUTPUT_FILE);
    }
}
