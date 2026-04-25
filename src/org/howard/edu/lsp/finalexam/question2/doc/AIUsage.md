# AI Usage – Question 2

## AI Tools Used
Claude (Claude Code CLI)

## Prompts Used
1. Provided the full Question 2 specification and asked Claude to scaffold the Template Method class hierarchy.
2. Asked Claude to verify the output format matched the expected output exactly.
3. Asked Claude to write the Driver demonstrating polymorphism with `List<Report>`.

## How AI Helped
Claude generated the abstract `Report` class with the `final` template method and the two concrete subclasses, then verified the output format matched the specification. It also wrote the Driver using the required `List<Report>` loop.

## Reflection
Working through the Template Method pattern made it clear how a `final` method in an abstract class enforces a workflow while still allowing subclass customization — a clean separation of the invariant algorithm from the variant steps.
