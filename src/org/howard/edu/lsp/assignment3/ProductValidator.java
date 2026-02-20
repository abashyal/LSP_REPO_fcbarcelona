package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Validates raw CSV row data and converts it into a {@link Product} object.
 *
 * <p>This class encapsulates all validation rules for incoming product records:
 * <ul>
 *   <li>A row must have exactly 4 comma-separated fields.</li>
 *   <li>The ProductID field must parse as a valid integer.</li>
 *   <li>The Price field must parse as a valid decimal number.</li>
 *   <li>All fields are trimmed of leading and trailing whitespace.</li>
 * </ul>
 * Rows that fail any of these checks are considered invalid and should be skipped.</p>
 *
 * @author Aayush Bashyal
 * @version 1.0
 */
public class ProductValidator {

    /** Expected number of fields in each data row. */
    private static final int EXPECTED_FIELD_COUNT = 4;

    /**
     * Attempts to parse and validate a raw CSV line into a {@link Product}.
     *
     * <p>Returns {@code null} if the line is blank, has the wrong number of fields,
     * contains a non-integer ProductID, or contains a non-numeric Price.</p>
     *
     * @param line the raw CSV line to validate (not including the header)
     * @return a valid {@link Product} if the line passes all checks, or {@code null} otherwise
     */
    public Product validate(String line) {
        // Skip blank lines
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        // Split into fields, keeping trailing empty strings
        String[] fields = line.split(",", -1);

        // Must have exactly 4 fields
        if (fields.length != EXPECTED_FIELD_COUNT) {
            return null;
        }

        // Trim whitespace from all fields
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].trim();
        }

        String productIdStr = fields[0];
        String name         = fields[1];
        String priceStr     = fields[2];
        String category     = fields[3];

        // Validate ProductID as integer
        int productId;
        try {
            productId = Integer.parseInt(productIdStr);
        } catch (NumberFormatException e) {
            return null;
        }

        // Validate Price as decimal
        BigDecimal price;
        try {
            price = new BigDecimal(priceStr);
        } catch (NumberFormatException e) {
            return null;
        }

        return new Product(productId, name, price, category);
    }
}
