# Development Log — Question 3

## External Resources Used

### AI Tool: Claude (Anthropic) — Claude Code CLI

The following is the complete conversation transcript used to help produce the solution for Question 3 (Refactoring to Use a Design Pattern).

---

**User:**
I need help with Part 1 of Question 3. Here is the `PriceCalculator` class:

```java
package org.howard.edu.lsp.midterm.strategy;

public class PriceCalculator {

    public double calculatePrice(String customerType, double price) {

        double finalPrice = price;

        if (customerType.equals("REGULAR")) { finalPrice = price; }
        if (customerType.equals("MEMBER"))  { finalPrice = price * 0.90; }
        if (customerType.equals("VIP"))     { finalPrice = price * 0.80; }
        if (customerType.equals("HOLIDAY")) { finalPrice = price * 0.85; }

        return finalPrice;
    }
}
```

Evaluate the design of this class and explain why it leads to maintenance and extensibility problems. Write the answer in `design_evaluation.md`.

---

**Claude:**
I identified 5 design issues and wrote `design_evaluation.md` covering:

1. **Open/Closed Principle violation** — Every new customer type requires modifying the existing `calculatePrice()` method. A well-designed system should allow extension without modifying existing code.

2. **Unbounded method growth** — As the number of customer types grows, the method becomes a long chain of conditionals that is hard to read and test.

3. **Fragile string-based type checking** — Raw string comparisons like `customerType.equals("MEMBER")` fail silently at runtime for typos. Using an `enum` would catch errors at compile time.

4. **Hardcoded magic numbers** — Discount multipliers like `0.90` and `0.80` are unexplained and hard to update without hunting through the method.

5. **No separation of algorithms** — All pricing strategies are collapsed into one method, making them impossible to reuse, swap, or independently test.

---

**User:**
Now do Part 2 — refactor the class using the Strategy Pattern.

---

**Claude:**
I implemented the Strategy Pattern with the following files:

- `PricingStrategy.java` — interface with `calculatePrice(double price)`
- `RegularPricingStrategy.java` — returns the original price with no discount
- `MemberPricingStrategy.java` — applies a 10% discount (`price * 0.90`)
- `VIPPricingStrategy.java` — applies a 20% discount (`price * 0.80`)
- `HolidayPricingStrategy.java` — applies a 15% discount (`price * 0.85`)
- `PriceCalculator.java` — context class that holds a `PricingStrategy` and delegates `calculatePrice()` to it; supports runtime strategy swapping via `setStrategy()`

Adding a new customer type now only requires creating a new class implementing `PricingStrategy` — no modifications to existing code.

---

**User:**
Now do Part 3 — create a Driver class and Part 4 — development log.

---

**Claude:**
I created `Driver.java` that constructs a `PriceCalculator` with each strategy using `setStrategy()` and prints the results for a purchase price of 100.0. The output matched the required format exactly:

```
REGULAR: 100.0
MEMBER: 90.0
VIP: 80.0
HOLIDAY: 85.0
```
