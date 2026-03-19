package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for holiday promotions.
 * Applies a 15% discount to the original price.
 *
 * @author Aayush Bashyal
 */
public class HolidayPricingStrategy implements PricingStrategy {

    /**
     * Calculates the final price with a 15% holiday discount applied.
     *
     * @param price the original price
     * @return the price after applying the 15% discount
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.85;
    }
}
