# Question 5 – Arthur Riel's Object-Oriented Design Heuristics

---

## Heuristic 1

**Name:** All data should be hidden within its class.

**Explanation:**
This heuristic states that the internal state (fields) of a class must be `private` and accessible only through the class's public interface (getters and setters). In lecture, this was illustrated using the `OrderProcessor` class from Assignment 3, where all four fields (`customerName`, `email`, `item`, `price`) were declared `public`. Because any outside class could directly read or mutate these fields, there was no way to enforce constraints on the data, and any code that touched those fields was tightly coupled to the internal representation. Making fields private and adding controlled accessors improves maintainability because the internal representation can change (e.g., splitting `customerName` into `firstName` and `lastName`) without breaking external callers. It also improves readability by making the class's intended interface explicit rather than exposing every implementation detail.

---

## Heuristic 2

**Name:** A class should capture one and only one key abstraction.

**Explanation:**
This heuristic states that each class should represent a single, cohesive concept from the problem domain and have exactly one reason to change. In lecture, this was illustrated with the `OrderProcessor` "god class," which simultaneously acted as a business logic processor, a file writer, an email sender, a receipt printer, and a logger. When a class takes on too many responsibilities, a change to any one of them — such as switching from file-based storage to a database — risks breaking the unrelated behaviors packed into the same class. The solution demonstrated in lecture was to distribute responsibilities across dedicated classes (`TaxCalculator`, `ReceiptPrinter`, `OrderRepository`, `EmailService`, `ActivityLogger`), each capturing a single abstraction. This makes classes smaller, independently testable, and easier to understand at a glance.

---

## Heuristic 3

**Name:** A method should do one thing and do it well (avoid god methods).

**Explanation:**
This heuristic states that a single method should perform one coherent task; a method that handles many unrelated concerns is called a "god method" and is a design smell. In lecture, this was demonstrated through the `processOrder()` method in `OrderProcessor`, which performed six distinct operations in sequence: calculate tax, print a receipt, save to file, send an email, apply a discount, and log the activity. Because all six steps were chained together in one method, a bug introduced when changing the discount logic could silently affect the receipt output — as actually happened in the example, where the discount was applied after the receipt had already been printed. The heuristic improves readability because short, single-purpose methods are easier to name accurately and to understand in isolation. It improves maintainability because each concern can be changed, tested, or replaced independently without risking side effects on the other steps.
