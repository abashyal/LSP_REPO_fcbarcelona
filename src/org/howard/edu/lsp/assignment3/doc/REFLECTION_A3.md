# Assignment 3 Reflection: Object-Oriented Redesign of the ETL Pipeline

**Author:** Aayush Bashyal
**Course:** CSCI 363
**Date:** February 2026

---

## 1. Design Differences: Assignment 2 vs. Assignment 3

### Assignment 2 Design

Assignment 2 used a single class, `ETLPipeline`, to do everything. That one class opened
files, read lines, validated field counts, parsed data types, applied all business
transformation rules, wrote the output, and printed the run summary. While this worked
correctly, it violated the Single Responsibility Principle: the class had many unrelated
reasons to change. Adding a new validation rule, swapping the output format, or changing
the transformation logic all required editing the same file.

### Assignment 3 Design

Assignment 3 decomposes the same behavior across six classes and one interface:

| Class / Interface       | Responsibility                                                         |
|-------------------------|------------------------------------------------------------------------|
| `Product`               | Data model — holds ProductID, Name, Price, Category, PriceRange       |
| `Transformer`           | Interface — defines the contract for any transformation step           |
| `ProductTransformer`    | Implements `Transformer` — applies all five business rules             |
| `ProductValidator`      | Validates raw CSV lines and converts them into `Product` objects       |
| `CSVReader`             | Extraction — opens the input file, reads the header, streams data rows |
| `CSVWriter`             | Loading — opens the output file, writes the header and product rows    |
| `ETLPipeline`           | Orchestrator — calls the above in sequence; owns the run summary       |

Each class now has one clear reason to change. If the output format changes, only
`CSVWriter` and `Product.toString()` need updating. If a new validation rule is added,
only `ProductValidator` changes. If a new discount rule is introduced, only
`ProductTransformer` changes.

---

## 2. Object-Oriented Concepts Applied

### Objects and Classes

Every meaningful concept in the pipeline is now its own class. A `Product` is an object
with state (its fields) and behavior (its `toString()` method). A `CSVReader` is an object
that encapsulates an open file handle and provides a clean interface to the pipeline.
Before Assignment 3, these concepts existed only as local variables scattered through one
long `run()` method.

### Encapsulation

The `Product` class encapsulates its five fields behind private instance variables and
exposes them only through public getters and setters. External code cannot directly read or
write `product.price`; it must go through `product.getPrice()` and `product.setPrice()`.
This protects the data and makes it possible to add validation inside setters in the future
without breaking any callers.

Similarly, `CSVReader` hides the `BufferedReader` as a private field. The pipeline never
touches the underlying Java I/O class directly; it calls `reader.open()`,
`reader.readLine()`, and `reader.close()`. The implementation details of how the file is
opened are invisible to the orchestrator.

### Inheritance and Polymorphism

The `Transformer` interface defines a single method, `transform(Product product)`, and
`ProductTransformer` implements it. Inside `ETLPipeline.run()`, the transformer is declared
as the interface type:

```java
Transformer transformer = new ProductTransformer();
```

This means the pipeline is written against the interface, not a concrete class. If a
different set of business rules were needed — for example, a `DiscountedTransformer` that
applies a 20% discount instead — it could be substituted without changing `ETLPipeline` at
all. This is runtime polymorphism: the call `transformer.transform(product)` dispatches to
whichever implementation is currently assigned. The `ProductTransformer` class also uses
the `@Override` annotation to make it explicit that it is fulfilling an inherited contract
from the `Transformer` interface.

---

## 3. Testing: Confirming Assignment 3 Matches Assignment 2

To confirm correctness, both pipelines were compiled and run against the same
`data/products.csv` input file, and their outputs were compared.

**Run summary comparison:**

| Metric           | Assignment 2 | Assignment 3 |
|------------------|--------------|--------------|
| Rows read        | 12           | 12           |
| Rows transformed | 7            | 7            |
| Rows skipped     | 5            | 5            |

**Output file comparison (`data/transformed_products.csv`):**

Both pipelines produced the following identical output:

```
ProductID,Name,Price,Category,PriceRange
7,USB CABLE,8.99,Electronics,Low
8,OFFICE CHAIR,150.00,Furniture,High
9,4K TV,1080.00,Premium Electronics,Premium
10,GIFT CARD,10.00,Other,Low
11,MOUSE,23.00,Electronics,Medium
12,TABLE,500.00,Furniture,High
13,CAMERA,500.40,Premium Electronics,Premium
```

**Edge cases verified:**

- **Missing input file:** Both pipelines print `Error: Input file 'data/products.csv' not
  found.` and exit gracefully when the input file is absent.
- **Empty input file:** Both pipelines write only the header row to the output and exit
  normally when the input file exists but is empty.
- **Invalid rows:** Rows with wrong field counts, non-integer ProductIDs, and non-numeric
  prices are skipped in both implementations, with identical skipped-row counts.

The refactoring into multiple classes did not change any observable behavior — all outputs,
error messages, and summary statistics are identical between Assignment 2 and Assignment 3.
