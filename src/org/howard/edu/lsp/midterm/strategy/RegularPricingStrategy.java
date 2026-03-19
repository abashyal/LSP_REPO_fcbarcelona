package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for regular customers.
 * No discount is applied; the original price is returned as-is.
 *
 * @author Aayush Bashyal
 */
public class RegularPricingStrategy implements PricingStrategy {

    /**
     * Returns the original price with no discount applied.
     *
     * @param price the original price
     * @return the original price unchanged
     */
    @Override
    public double calculatePrice(double price) {
        return price;
    }
}
