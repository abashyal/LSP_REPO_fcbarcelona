# Design Evaluation: OrderProcessor Class

## Overview

The `OrderProcessor` class contains several significant object-oriented design issues that violate core OO principles and Arthur Riel's design heuristics. The issues are described below.

---

## Issue 1: Poor Encapsulation (Public Fields)

All four fields — `customerName`, `email`, `item`, and `price` — are declared `public`. This means any external class can directly read or modify them without any validation or control. According to Riel's heuristics, all data should be hidden within its class. Public fields break encapsulation, eliminate the ability to enforce constraints on data, and tightly couple any code that accesses these fields directly to the internal representation of the class. These fields should be `private` with appropriate getter and setter methods.

---

## Issue 2: Single Responsibility Principle Violation (God Method)

The `processOrder()` method handles six distinct responsibilities in a single block of code:

1. Calculating tax
2. Printing a receipt to the console
3. Saving the order to a file
4. Sending a confirmation email
5. Applying a discount
6. Logging the activity

This is a classic "God method" — a method that knows too much and does too much. According to Riel's heuristics, a method should do one thing and do it well. When a single method handles unrelated concerns, any change to one behavior (e.g., changing the tax rate or switching from file storage to a database) risks breaking the others. This makes the code fragile, hard to test, and hard to maintain.

---

## Issue 3: Single Responsibility Principle Violation (God Class)

Beyond the method, the `OrderProcessor` class itself takes on too many responsibilities: it acts as a business logic processor, a file writer, an email sender, a receipt printer, and a logger all at once. Riel's heuristics state that a class should capture one and only one key abstraction. A well-designed system would distribute these responsibilities across dedicated classes such as `Order`, `Customer`, `TaxCalculator`, `ReceiptPrinter`, `OrderRepository`, `EmailService`, and `ActivityLogger`. This would make each class focused, independently testable, and easier to extend.

---

## Issue 4: Logical Error — Discount Applied After Receipt and File Save

The discount logic appears after the receipt has already been printed and after the order has already been written to the file. This means:

- The customer's printed receipt shows the **pre-discount total**
- The file also records the **pre-discount total**
- But the `total` variable is updated with the discount after the fact, with no effect on either output

This is a correctness bug caused directly by cramming multiple responsibilities into one sequential method. Proper separation of concerns — computing the final total before any output — would prevent this error.

---

## Issue 5: Missing Domain Abstractions

The class uses primitive fields (`customerName`, `email`, `item`, `price`) instead of meaningful domain objects. A proper OO design would introduce:

- A `Customer` class to encapsulate `customerName` and `email`
- An `Order` class to encapsulate `item`, `price`, tax, discount, and total

Riel's heuristics warn against classes that treat other objects as data fields rather than collaborators. Using raw primitives instead of objects prevents behavior from being properly distributed and reduces the expressiveness of the design.

---

## Summary

| Issue | Design Principle Violated |
|---|---|
| Public fields | Encapsulation / Riel: hide all data |
| processOrder() does 6 things | Single Responsibility Principle / Riel: one purpose per method |
| OrderProcessor handles all concerns | Single Responsibility Principle / Riel: one key abstraction per class |
| Discount applied after output | Poor logical ordering from mixed responsibilities |
| No Customer or Order class | Missing domain abstractions / Riel: model the real world |

The overall design leads to a class that is difficult to test, impossible to extend without modification, and highly prone to bugs — the opposite of what good object-oriented design aims to achieve.
