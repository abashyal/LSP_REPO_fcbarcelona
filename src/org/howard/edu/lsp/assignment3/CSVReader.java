package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Handles the extraction phase of the ETL pipeline by reading from a CSV file.
 *
 * <p>This class abstracts all file I/O concerns for input away from the pipeline
 * orchestrator. It opens the specified file, skips the header row, and provides
 * access to subsequent data lines one at a time.</p>
 *
 * <p>Usage pattern:</p>
 * <pre>
 *   CSVReader reader = new CSVReader("data/products.csv");
 *   if (reader.open()) {
 *       String line;
 *       while ((line = reader.readLine()) != null) {
 *           // process line
 *       }
 *       reader.close();
 *   }
 * </pre>
 *
 * @author Aayush Bashyal
 * @version 1.0
 */
public class CSVReader {

    /** Path to the CSV input file. */
    private final String filePath;

    /** Underlying buffered reader for the input file. */
    private BufferedReader reader;

    /** Whether the file was opened and a header line was found. */
    private boolean hasHeader;

    /**
     * Constructs a CSVReader for the given file path.
     *
     * @param filePath relative or absolute path to the input CSV file
     */
    public CSVReader(String filePath) {
        this.filePath = filePath;
        this.hasHeader = false;
    }

    /**
     * Opens the CSV file and reads past the header row.
     *
     * <p>If the file does not exist or cannot be opened, an error message is
     * printed to standard error and this method returns {@code false}.
     * If the file is empty (no header), {@code hasHeader} remains {@code false}
     * and the file is considered valid but empty.</p>
     *
     * @return {@code true} if the file was opened successfully, {@code false} otherwise
     */
    public boolean open() {
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String headerLine = reader.readLine();
            hasHeader = (headerLine != null);
            return true;
        } catch (IOException e) {
            System.err.println("Error: Input file '" + filePath + "' not found.");
            return false;
        }
    }

    /**
     * Reads and returns the next data line from the CSV file.
     *
     * <p>Returns {@code null} when the end of the file is reached or if the
     * file has no header (and thus no data rows to read).</p>
     *
     * @return the next raw CSV line, or {@code null} if there are no more lines
     * @throws IOException if an I/O error occurs while reading
     */
    public String readLine() throws IOException {
        if (!hasHeader || reader == null) {
            return null;
        }
        return reader.readLine();
    }

    /**
     * Returns whether the opened file contained a header line.
     *
     * @return {@code true} if a header was found, {@code false} if the file was empty
     */
    public boolean hasHeader() {
        return hasHeader;
    }

    /**
     * Closes the underlying file reader and releases system resources.
     *
     * <p>Safe to call even if the file was never successfully opened.</p>
     */
    public void close() {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println("Error closing input file: " + e.getMessage());
            }
        }
    }
}
