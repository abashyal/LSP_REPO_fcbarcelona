package org.howard.edu.lsp.midterm.strategy;

/**
 * Context class for the Strategy pattern.
 * Uses a PricingStrategy to calculate the final price for a customer purchase.
 * New pricing behaviors can be added by implementing PricingStrategy
 * without modifying this class.
 *
 * @author Aayush Bashyal
 */
public class PriceCalculator {

    private PricingStrategy strategy;

    /**
     * Constructs a PriceCalculator with the given pricing strategy.
     *
     * @param strategy the pricing strategy to use for calculations
     */
    public PriceCalculator(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Sets a new pricing strategy at runtime.
     *
     * @param strategy the new pricing strategy to apply
     */
    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates the final price using the current pricing strategy.
     *
     * @param price the original price before discount
     * @return the final price after applying the strategy's discount
     */
    public double calculatePrice(double price) {
        return strategy.calculatePrice(price);
    }
}
