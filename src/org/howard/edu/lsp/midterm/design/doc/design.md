# Proposed Improved Design: CRC Cards

## Overview

The redesigned system distributes the responsibilities of the original `OrderProcessor` class across eight focused classes. Each class captures a single abstraction, and collaboration between classes is explicit and minimal.

---

## CRC Cards

---

**Class:** `Customer`

**Responsibilities:**
- Store customer name and email
- Provide access to customer information

**Collaborators:**
- None

---

**Class:** `Order`

**Responsibilities:**
- Store order item and price
- Hold the computed tax, discount, and final total
- Provide access to all order details

**Collaborators:**
- `Customer`

---

**Class:** `TaxCalculator`

**Responsibilities:**
- Calculate the tax amount for a given price
- Return the computed tax value

**Collaborators:**
- `Order`

---

**Class:** `DiscountCalculator`

**Responsibilities:**
- Determine whether a discount applies based on the order price
- Calculate and return the discounted total

**Collaborators:**
- `Order`

---

**Class:** `ReceiptPrinter`

**Responsibilities:**
- Format and print the order receipt to the console
- Display customer name, item, and final total

**Collaborators:**
- `Order`
- `Customer`

---

**Class:** `OrderRepository`

**Responsibilities:**
- Save order details to a file
- Handle file I/O and any related errors

**Collaborators:**
- `Order`
- `Customer`

---

**Class:** `EmailService`

**Responsibilities:**
- Send a confirmation email to the customer
- Format the email message with order details

**Collaborators:**
- `Customer`
- `Order`

---

**Class:** `ActivityLogger`

**Responsibilities:**
- Log the timestamp of when an order was processed
- Record activity for auditing purposes

**Collaborators:**
- None

---

**Class:** `OrderProcessor`

**Responsibilities:**
- Coordinate the full order processing workflow
- Invoke tax calculation, discount calculation, receipt printing, file saving, email sending, and logging in the correct order

**Collaborators:**
- `Order`
- `Customer`
- `TaxCalculator`
- `DiscountCalculator`
- `ReceiptPrinter`
- `OrderRepository`
- `EmailService`
- `ActivityLogger`

---

## Summary

| Class | Key Responsibility |
|---|---|
| `Customer` | Store customer identity |
| `Order` | Store order data and computed totals |
| `TaxCalculator` | Compute tax |
| `DiscountCalculator` | Compute discount |
| `ReceiptPrinter` | Print receipt |
| `OrderRepository` | Persist order to file |
| `EmailService` | Send confirmation email |
| `ActivityLogger` | Log processing timestamp |
| `OrderProcessor` | Orchestrate the workflow |

This design ensures each class has one clear responsibility, making the system easier to test, maintain, and extend independently.
