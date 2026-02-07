package org.howard.edu.lsp.assignment2;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {
    
    private static final String INPUT_FILE = "data/products.csv";
    private static final String OUTPUT_FILE = "data/transformed_products.csv";
    
    private int rowsRead = 0;
    private int rowsTransformed = 0;
    private int rowsSkipped = 0;
    
    public static void main(String[] args) {
        ETLPipeline pipeline = new ETLPipeline();
        pipeline.run();
    }
    
    public void run() {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        
        try {
            // Extract - Open input file
            try {
                reader = new BufferedReader(new FileReader(INPUT_FILE));
            } catch (IOException e) {
                System.err.println("Error: Input file '" + INPUT_FILE + "' not found.");
                return;
            }
            
            // Open output file
            try {
                writer = new BufferedWriter(new FileWriter(OUTPUT_FILE));
            } catch (IOException e) {
                System.err.println("Error: Could not create output file '" + OUTPUT_FILE + "'.");
                if (reader != null) reader.close();
                return;
            }
            
            // Read header
            String headerLine = reader.readLine();
            if (headerLine == null) {
                // Empty file - write header only
                writer.write("ProductID,Name,Price,Category,PriceRange");
                writer.newLine();
            } else {
                // Write output header
                writer.write("ProductID,Name,Price,Category,PriceRange");
                writer.newLine();
                
                // Process each data row
                String line;
                while ((line = reader.readLine()) != null) {
                    rowsRead++;
                    
                    // Skip blank lines
                    if (line.trim().isEmpty()) {
                        rowsSkipped++;
                        continue;
                    }
                    
                    // Parse and validate row
                    String[] fields = line.split(",", -1);
                    
                    // Must have exactly 4 fields
                    if (fields.length != 4) {
                        rowsSkipped++;
                        continue;
                    }
                    
                    // Trim whitespace from all fields
                    for (int i = 0; i < fields.length; i++) {
                        fields[i] = fields[i].trim();
                    }
                    
                    // Extract fields
                    String productIdStr = fields[0];
                    String name = fields[1];
                    String priceStr = fields[2];
                    String category = fields[3];
                    
                    // Validate ProductID (must be integer)
                    int productId;
                    try {
                        productId = Integer.parseInt(productIdStr);
                    } catch (NumberFormatException e) {
                        rowsSkipped++;
                        continue;
                    }
                    
                    // Validate Price (must be decimal)
                    BigDecimal price;
                    try {
                        price = new BigDecimal(priceStr);
                    } catch (NumberFormatException e) {
                        rowsSkipped++;
                        continue;
                    }
                    
                    // Transform the row
                    String transformedRow = transform(productId, name, price, category);
                    
                    // Load - Write to output
                    writer.write(transformedRow);
                    writer.newLine();
                    rowsTransformed++;
                }
            }
            
        } catch (IOException e) {
            System.err.println("Error reading or writing files: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.err.println("Error closing files: " + e.getMessage());
            }
        }
        
        // Print run summary
        printSummary();
    }
    
    private String transform(int productId, String name, BigDecimal price, String category) {
        // Step 1: Convert name to UPPERCASE
        name = name.toUpperCase();
        
        // Step 2: If category is "Electronics", apply 10% discount
        String originalCategory = category;
        if (category.equals("Electronics")) {
            price = price.multiply(new BigDecimal("0.90"));
        }
        
        // Round price to 2 decimal places (round-half-up)
        price = price.setScale(2, RoundingMode.HALF_UP);
        
        // Step 3: If final rounded price > $500.00 AND original category was "Electronics",
        // change category to "Premium Electronics"
        if (price.compareTo(new BigDecimal("500.00")) > 0 && originalCategory.equals("Electronics")) {
            category = "Premium Electronics";
        }
        
        // Step 4: Add PriceRange based on final rounded price
        String priceRange;
        if (price.compareTo(new BigDecimal("10.00")) <= 0) {
            priceRange = "Low";
        } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
            priceRange = "Medium";
        } else if (price.compareTo(new BigDecimal("500.00")) <= 0) {
            priceRange = "High";
        } else {
            priceRange = "Premium";
        }
        
        // Format output row
        return productId + "," + name + "," + price.toPlainString() + "," + category + "," + priceRange;
    }
    
    private void printSummary() {
        System.out.println("=== ETL Pipeline Run Summary ===");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output file: " + OUTPUT_FILE);
    }
}
