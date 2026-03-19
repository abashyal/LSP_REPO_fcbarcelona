package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for VIP customers.
 * Applies a 20% discount to the original price.
 *
 * @author Aayush Bashyal
 */
public class VIPPricingStrategy implements PricingStrategy {

    /**
     * Calculates the final price with a 20% VIP discount applied.
     *
     * @param price the original price
     * @return the price after applying the 20% discount
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.80;
    }
}
