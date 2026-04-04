# AI Usage Report – Assignment 5: IntegerSet Implementation

## AI Tools Used

### Claude (claude.ai / Claude Code CLI)
- Used to scaffold the `IntegerSet.java` implementation including all required methods.
- Used to create `IntegerSetException.java` for custom exception handling.
- Used to verify correctness by running `Driver.java` and reviewing output.

### Conversation Summary
- Prompted Claude with the full assignment specification.
- Claude generated `IntegerSet.java` with Javadoc comments, proper encapsulation, and all required set operations using `ArrayList` and Java Collections Framework methods (`retainAll`, `removeAll`, `Collections.sort`, `Collections.max`, `Collections.min`).
- Claude generated `IntegerSetException.java` as a custom `RuntimeException` for `largest()` and `smallest()` on empty sets.
- Output was verified to match the expected `Driver.java` output exactly.

## External References

- Java Collections Framework documentation: https://docs.oracle.com/en/java/docs/
- `ArrayList`, `Collections.sort`, `Collections.max`, `Collections.min` – standard JCF usage.
