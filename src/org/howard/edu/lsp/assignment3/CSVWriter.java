package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles the load phase of the ETL pipeline by writing transformed records to a CSV file.
 *
 * <p>This class abstracts all file I/O concerns for output away from the pipeline
 * orchestrator. It opens the specified output file, writes the standard header,
 * and exposes a method to write one transformed {@link Product} per call.</p>
 *
 * <p>Usage pattern:</p>
 * <pre>
 *   CSVWriter writer = new CSVWriter("data/transformed_products.csv");
 *   if (writer.open()) {
 *       writer.writeHeader();
 *       writer.writeProduct(product);
 *       writer.close();
 *   }
 * </pre>
 *
 * @author Aayush Bashyal
 * @version 1.0
 */
public class CSVWriter {

    /** Path to the CSV output file. */
    private final String filePath;

    /** Underlying buffered writer for the output file. */
    private BufferedWriter writer;

    /** Header row written to every output file. */
    private static final String HEADER = "ProductID,Name,Price,Category,PriceRange";

    /**
     * Constructs a CSVWriter targeting the given file path.
     *
     * @param filePath relative or absolute path to the output CSV file
     */
    public CSVWriter(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Opens the output file for writing, creating it if it does not exist.
     *
     * <p>If the file cannot be created or opened, an error message is printed
     * to standard error and this method returns {@code false}.</p>
     *
     * @return {@code true} if the file was opened successfully, {@code false} otherwise
     */
    public boolean open() {
        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            return true;
        } catch (IOException e) {
            System.err.println("Error: Could not create output file '" + filePath + "'.");
            return false;
        }
    }

    /**
     * Writes the standard CSV header row to the output file.
     *
     * <p>The header written is: {@code ProductID,Name,Price,Category,PriceRange}.</p>
     *
     * @throws IOException if an I/O error occurs while writing
     */
    public void writeHeader() throws IOException {
        writer.write(HEADER);
        writer.newLine();
    }

    /**
     * Writes a single transformed product as a CSV row to the output file.
     *
     * <p>The product's {@link Product#toString()} method is used to produce
     * the CSV-formatted line.</p>
     *
     * @param product the transformed product to write
     * @throws IOException if an I/O error occurs while writing
     */
    public void writeProduct(Product product) throws IOException {
        writer.write(product.toString());
        writer.newLine();
    }

    /**
     * Closes the underlying file writer and flushes any buffered output.
     *
     * <p>Safe to call even if the file was never successfully opened.</p>
     */
    public void close() {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                System.err.println("Error closing output file: " + e.getMessage());
            }
        }
    }
}
