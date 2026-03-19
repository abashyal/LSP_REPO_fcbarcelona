package org.howard.edu.lsp.midterm.strategy;

/**
 * Strategy interface for pricing algorithms.
 * Each implementation defines a specific discount behavior
 * for a customer type.
 *
 * @author Aayush Bashyal
 */
public interface PricingStrategy {

    /**
     * Calculates the final price after applying the strategy's discount.
     *
     * @param price the original price before discount
     * @return the final price after applying the discount
     */
    double calculatePrice(double price);
}
