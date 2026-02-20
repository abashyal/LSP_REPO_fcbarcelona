package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Applies all business transformation rules to a {@link Product}.
 *
 * <p>Implements the {@link Transformer} interface, demonstrating polymorphism.
 * The transformation steps applied are:
 * <ol>
 *   <li>Convert the product name to uppercase.</li>
 *   <li>Apply a 10% discount to products in the "Electronics" category.</li>
 *   <li>Round the price to 2 decimal places (HALF_UP).</li>
 *   <li>Reclassify Electronics items whose final price exceeds $500.00
 *       as "Premium Electronics".</li>
 *   <li>Assign a PriceRange label based on the final rounded price.</li>
 * </ol>
 * </p>
 *
 * @author Aayush Bashyal
 * @version 1.0
 */
public class ProductTransformer implements Transformer {

    /** Discount multiplier applied to Electronics products (10% off). */
    private static final BigDecimal ELECTRONICS_DISCOUNT = new BigDecimal("0.90");

    /** Price threshold above which Electronics become "Premium Electronics". */
    private static final BigDecimal PREMIUM_THRESHOLD = new BigDecimal("500.00");

    /** Upper bound (inclusive) for the "Low" price range. */
    private static final BigDecimal LOW_MAX = new BigDecimal("10.00");

    /** Upper bound (inclusive) for the "Medium" price range. */
    private static final BigDecimal MEDIUM_MAX = new BigDecimal("100.00");

    /** Upper bound (inclusive) for the "High" price range. */
    private static final BigDecimal HIGH_MAX = new BigDecimal("500.00");

    /**
     * Transforms the given product by applying all business rules in sequence.
     *
     * <p>The product is modified in place and returned. The original category
     * is preserved internally to correctly apply the "Premium Electronics"
     * reclassification rule after discounting.</p>
     *
     * @param product the product to transform; must not be {@code null}
     * @return the same product instance after all transformations have been applied
     */
    @Override
    public Product transform(Product product) {
        // Step 1: Convert name to uppercase
        product.setName(product.getName().toUpperCase());

        // Preserve original category before any modification
        String originalCategory = product.getCategory();

        // Step 2: Apply 10% discount for Electronics
        if (originalCategory.equals("Electronics")) {
            product.setPrice(product.getPrice().multiply(ELECTRONICS_DISCOUNT));
        }

        // Step 3: Round price to 2 decimal places (HALF_UP)
        product.setPrice(product.getPrice().setScale(2, RoundingMode.HALF_UP));

        // Step 4: Reclassify to "Premium Electronics" if applicable
        if (product.getPrice().compareTo(PREMIUM_THRESHOLD) > 0
                && originalCategory.equals("Electronics")) {
            product.setCategory("Premium Electronics");
        }

        // Step 5: Assign PriceRange label
        product.setPriceRange(determinePriceRange(product.getPrice()));

        return product;
    }

    /**
     * Determines the price range label for a given price.
     *
     * <ul>
     *   <li>"Low"     — price &le; $10.00</li>
     *   <li>"Medium"  — $10.00 &lt; price &le; $100.00</li>
     *   <li>"High"    — $100.00 &lt; price &le; $500.00</li>
     *   <li>"Premium" — price &gt; $500.00</li>
     * </ul>
     *
     * @param price the final rounded price
     * @return the appropriate price range label
     */
    private String determinePriceRange(BigDecimal price) {
        if (price.compareTo(LOW_MAX) <= 0) {
            return "Low";
        } else if (price.compareTo(MEDIUM_MAX) <= 0) {
            return "Medium";
        } else if (price.compareTo(HIGH_MAX) <= 0) {
            return "High";
        } else {
            return "Premium";
        }
    }
}
