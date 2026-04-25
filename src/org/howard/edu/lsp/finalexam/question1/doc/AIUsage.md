# AI Usage – Question 1

## AI Tools Used
Claude (Claude Code CLI)

## Prompts Used
1. Explained the full Question 1 spec and asked Claude to help structure the answers according to the required format.
2. Asked Claude to review the concurrency analysis for correctness and completeness.

## How AI Helped
Claude helped organize the answers into the required format and verified the concurrency reasoning for each fix. It also suggested the `AtomicInteger` + `CopyOnWriteArrayList` approach for Part 4 and provided a clean code snippet.

## Reflection
This question reinforced that synchronizing the right method at the right granularity matters — synchronizing a getter when the writer is the problem accomplishes nothing.
