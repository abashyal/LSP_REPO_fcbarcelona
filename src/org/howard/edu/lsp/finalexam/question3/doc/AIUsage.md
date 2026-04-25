# AI Usage – Question 3

## AI Tools Used
Claude (Claude Code CLI)

## Prompts Used
1. Provided the GradeCalculator source and Question 3 requirements; asked Claude to write a JUnit 5 test class covering all required categories.
2. Asked Claude to verify that boundary tests at exactly 60 and 90 were included and that two distinct `assertThrows` tests were present.

## How AI Helped
Claude generated the full test class with correctly named test methods, boundary cases at the grade thresholds (60 and 90), and two `assertThrows` tests for out-of-range scores. It also ensured the `@BeforeEach` setup and `@DisplayName` annotations followed the project's existing style.

## Reflection
Writing boundary tests for the grade thresholds highlighted how off-by-one errors at boundary values are among the most common bugs — testing exactly at 60 and 90 rather than 61 and 91 is essential.
