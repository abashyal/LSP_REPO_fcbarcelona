# Development Log — Question 2

## External Resources Used

### AI Tool: Claude (Anthropic) — Claude Code CLI

The following is the complete conversation transcript used to help produce the solution for Question 2 (Evaluating Object-Oriented Design).

---

**User:**
I need help with Part 1 of Question 2. Here is the `OrderProcessor` class:

```java
import java.io.FileWriter;
import java.util.Date;

public class OrderProcessor {

    public String customerName;
    public String email;
    public String item;
    public double price;

    public void processOrder() {

        double tax = price * 0.07;
        double total = price + tax;

        System.out.println("Customer: " + customerName);
        System.out.println("Item: " + item);
        System.out.println("Total: " + total);

        try {
            FileWriter writer = new FileWriter("orders.txt", true);
            writer.write(customerName + "," + item + "," + total + "\n");
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println("Sending confirmation email to " + email);

        if(price > 500) {
            total = total * 0.9;
        }

        System.out.println("Order processed at " + new Date());
    }
}
```

Evaluate the design of this class based on object-oriented design principles and Arthur Riel's heuristics. Write the evaluation in `evaluation.md`.

---

**Claude:**
I identified 5 design issues and wrote `evaluation.md` covering:

1. **Poor encapsulation** — All four fields are `public`, violating Riel's heuristic that all data should be hidden within its class. They should be `private` with getters and setters.

2. **God method** — `processOrder()` does six unrelated things: calculates tax, prints a receipt, saves to a file, sends an email, applies a discount, and logs activity. A method should do one thing well.

3. **God class** — `OrderProcessor` acts as a business logic processor, file writer, email sender, receipt printer, and logger all at once. Riel's heuristics state a class should capture one and only one key abstraction.

4. **Logical bug** — The discount is applied after the receipt is already printed and after the order is already saved to the file, so neither output reflects the correct discounted total.

5. **Missing domain abstractions** — Raw primitives are used instead of meaningful objects like `Customer` and `Order`, preventing proper distribution of behavior.

---

**User:**
Now do Part 2 — redesign the system using CRC cards and write the result in `design.md`.

---

**Claude:**
I redesigned the system into 9 focused classes and wrote `design.md` with CRC cards for each:

- `Customer` — stores customer name and email; no collaborators
- `Order` — stores item, price, and computed totals; collaborates with `Customer`
- `TaxCalculator` — computes tax; collaborates with `Order`
- `DiscountCalculator` — computes discount; collaborates with `Order`
- `ReceiptPrinter` — prints receipt; collaborates with `Order` and `Customer`
- `OrderRepository` — saves order to file; collaborates with `Order` and `Customer`
- `EmailService` — sends confirmation email; collaborates with `Customer` and `Order`
- `ActivityLogger` — logs timestamp; no collaborators
- `OrderProcessor` — orchestrates the full workflow; collaborates with all above classes

This design ensures each class has a single, clear responsibility, making the system testable, maintainable, and extensible independently.
