# AI Prompts Transcript — Assignment 3

**Author:** Aayush Bashyal
**Course:** CSCI 363
**Tool used:** Claude (claude-sonnet-4-6 via Claude Code CLI)

---

## Prompt 1

**Me:**
> I have an Assignment 2 ETL pipeline written in a single Java class. The professor wants
> me to redesign it to be more object-oriented for Assignment 3. Can you look at my
> ETLPipeline.java and suggest how to split it into multiple classes?

**AI Response (summary):**
The AI reviewed the single-class design and identified four major responsibilities that
could each become their own class: (1) the data model (a `Product` class), (2) validation
logic, (3) transformation logic, and (4) file I/O. It suggested splitting the monolithic
`run()` method across `ProductValidator`, `ProductTransformer`, `CSVReader`, and
`CSVWriter`, with a slim `ETLPipeline` orchestrator tying them together.

---

## Prompt 2

**Me:**
> Which OO concepts from class would this redesign demonstrate? I need to specifically
> cover: object, class, encapsulation, inheritance, and polymorphism.

**AI Response (summary):**
The AI walked through each concept:
- **Object/Class:** Each concept (product, validator, transformer, reader, writer) becomes
  a concrete class with its own state and behavior.
- **Encapsulation:** `Product` hides its fields behind private variables with getters and
  setters; `CSVReader` hides the `BufferedReader` from the pipeline.
- **Inheritance/Polymorphism:** Creating a `Transformer` interface that `ProductTransformer`
  implements allows the pipeline to program against the interface type, not the concrete
  class. It noted that declaring `Transformer transformer = new ProductTransformer()` in
  the orchestrator demonstrates runtime polymorphism.

---

## Prompt 3

**Me:**
> How should I structure the Transformer interface? Should it be a functional interface
> with a single transform() method, or should I give it multiple methods?

**AI Response (summary):**
The AI recommended a single-method functional interface annotated with `@FunctionalInterface`
conceptually, since the entire transformation for one product is one logical operation. It
suggested the signature `Product transform(Product product)` so the transformer receives a
product and returns the (possibly same) transformed product. This keeps the interface simple
and the pipeline code clean: `product = transformer.transform(product)`.

---

## Prompt 4

**Me:**
> For CSVReader, should it expose each field as a separate getter, or just return raw
> lines and let the validator parse them? What's the cleaner OO design?

**AI Response (summary):**
The AI argued for returning raw lines and delegating parsing to `ProductValidator`. Its
reasoning: `CSVReader`'s responsibility is file I/O, not interpretation. Mixing field
parsing into the reader would violate the Single Responsibility Principle and make it
harder to reuse `CSVReader` for other CSV files. The validator is the right place to know
about field count, data types, and trimming rules.

---

## Prompt 5

**Me:**
> Can you help me write Javadoc comments for the ProductTransformer class? I want to make
> sure I document the five transformation steps clearly.

**AI Response (summary):**
The AI generated a class-level Javadoc that listed all five transformation steps in an
ordered `<ol>` list, and wrote method-level Javadocs for `transform()` and the private
`determinePriceRange()` helper. I reviewed the generated docs against the actual
implementation and edited them to accurately reflect the 10% discount logic (multiply by
0.90) and the exact threshold values ($10, $100, $500) used for price range assignment.

---

## Prompt 6

**Me:**
> My A3 row counts don't match A2. A2 shows "Rows read: 12" but A3 shows "Rows read: 11".
> In A2, rowsRead++ happens before the blank-line check. How should I fix my ETLPipeline
> to match exactly?

**AI Response (summary):**
The AI identified that in Assignment 2, `rowsRead++` increments unconditionally for every
non-null line — including blank lines — before the `if (line.trim().isEmpty())` guard. My
Assignment 3 orchestrator had the blank-line check before the increment, causing the
discrepancy. The fix was to move `rowsRead++` to before the blank-line check, matching A2's
behavior exactly. After the fix, both pipelines produced `Rows read: 12`.

---

## How I Used and Adapted the AI's Suggestions

The AI's high-level class decomposition aligned well with what the assignment asked for, so
I adopted it directly. However, I made several specific decisions that differed from or
extended the AI's initial suggestions:

1. **Counting logic:** The AI's initial ETLPipeline draft counted rows slightly differently
   than Assignment 2. I caught this by comparing outputs side by side and corrected the
   order of operations.

2. **Javadocs:** I reviewed every Javadoc the AI generated and edited threshold values,
   field names, and return-value descriptions to match the actual code precisely.

3. **`ProductValidator` returning null:** The AI suggested throwing a custom exception for
   invalid rows. I chose to return `null` instead, matching the simpler style of Assignment
   2 and avoiding unnecessary complexity for this scope.

4. **`CSVReader.hasHeader()`:** The AI did not initially include this method. I added it so
   the pipeline could distinguish between an empty file (no header) and a file with a
   header but no data rows, matching the edge-case behavior of Assignment 2.
