package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for member customers.
 * Applies a 10% discount to the original price.
 *
 * @author Aayush Bashyal
 */
public class MemberPricingStrategy implements PricingStrategy {

    /**
     * Calculates the final price with a 10% member discount applied.
     *
     * @param price the original price
     * @return the price after applying the 10% discount
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.90;
    }
}
