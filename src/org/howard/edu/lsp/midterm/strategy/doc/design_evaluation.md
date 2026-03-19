# Design Evaluation: PriceCalculator Class

## Overview

The `PriceCalculator` class works correctly for the four customer types currently defined, but its design has several issues that will make the system increasingly difficult to maintain and extend as requirements evolve.

---

## Issue 1: Open/Closed Principle Violation

The most significant problem is that the `calculatePrice()` method must be directly modified every time a new customer type is introduced. If a new type such as `"STUDENT"` or `"EMPLOYEE"` needs to be supported, a developer must open the existing method and add another `if` block. According to the Open/Closed Principle, a class should be open for extension but closed for modification. A well-designed system should allow new pricing behaviors to be added without touching existing, tested code. The current design does the opposite — every extension requires modifying the core method, increasing the risk of introducing bugs.

---

## Issue 2: Unbounded Growth and Poor Maintainability

As the number of customer types grows, the `calculatePrice()` method grows with them. With dozens of customer types, the method becomes a long chain of conditionals that is hard to read, hard to test, and hard to reason about. Each new `if` block increases the method's complexity and the number of paths that need to be tested. This is a classic symptom of code that was not designed for change.

---

## Issue 3: String-Based Type Checking is Fragile

Customer types are identified using raw string comparisons (e.g., `customerType.equals("MEMBER")`). This is fragile because typos in the string — such as passing `"member"` or `"Vip"` — will silently fail at runtime rather than being caught at compile time. There is no enforcement that callers use valid values, and no IDE or compiler support to detect invalid types. Using an `enum` instead of a `String` would eliminate this entire class of errors.

---

## Issue 4: Hardcoded Magic Numbers

The discount multipliers (`0.90`, `0.80`, `0.85`) are embedded directly in the method with no explanation of what they represent or where they come from. If a business rule changes — for example, the VIP discount changes from 20% to 25% — a developer must hunt through the method to find and update the correct value. Named constants or encapsulated strategy objects would make these values explicit and easy to update.

---

## Issue 5: No Separation of Pricing Algorithms

All four pricing strategies are collapsed into a single method, making it impossible to reuse, swap, or independently test individual strategies. For example, if the `HOLIDAY` pricing strategy needed to apply a time-based condition in the future, the only option is to add more logic into an already overloaded method. The Strategy design pattern would solve this by encapsulating each pricing algorithm in its own class, allowing them to be developed, tested, and extended independently.

---

## Summary

| Issue | Impact |
|---|---|
| Open/Closed Principle violation | Must modify existing code to add new types |
| Unbounded method growth | Becomes unreadable and hard to test as types increase |
| String-based type checking | Fragile; errors not caught at compile time |
| Hardcoded magic numbers | Hard to understand and update discount values |
| No separation of algorithms | Cannot reuse or independently test individual strategies |

The Strategy design pattern is the appropriate solution here. It would encapsulate each pricing algorithm in its own class implementing a common interface, allowing new customer types to be added by writing new classes rather than modifying existing ones.
