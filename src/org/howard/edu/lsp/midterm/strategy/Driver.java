package org.howard.edu.lsp.midterm.strategy;

/**
 * Driver class that demonstrates the Strategy Pattern implementation
 * for the PriceCalculator system.
 *
 * @author Aayush Bashyal
 */
public class Driver {

    /**
     * Main method that tests all four pricing strategies using a purchase price of 100.0.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        PriceCalculator calculator = new PriceCalculator(new RegularPricingStrategy());
        System.out.println("REGULAR: " + calculator.calculatePrice(100.0));

        calculator.setStrategy(new MemberPricingStrategy());
        System.out.println("MEMBER: " + calculator.calculatePrice(100.0));

        calculator.setStrategy(new VIPPricingStrategy());
        System.out.println("VIP: " + calculator.calculatePrice(100.0));

        calculator.setStrategy(new HolidayPricingStrategy());
        System.out.println("HOLIDAY: " + calculator.calculatePrice(100.0));
    }
}
