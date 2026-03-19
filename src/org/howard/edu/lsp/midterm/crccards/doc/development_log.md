# Development Log

## External Resources Used

### AI Tool: Claude (Anthropic) — Claude Code CLI

The following is the complete conversation transcript used to help produce the solution for Question 1 (Task Management System).

---

**User:**
I need help implementing a Task Management System in Java for my midterm. Here are the requirements:

- Implement a `Task` class with fields `taskId`, `description`, and `status`
- Constructor: `public Task(String taskId, String description)` — default status must be `"OPEN"`
- Valid statuses: `OPEN`, `IN_PROGRESS`, `COMPLETE` (case-sensitive). Any other value sets status to `"UNKNOWN"`
- `toString()` must produce output in the format: `T1 Write report [OPEN]`
- Implement a `TaskManager` class that stores tasks, adds tasks (throwing `IllegalArgumentException` on duplicate IDs), finds a task by ID (returning `null` if not found), and returns tasks filtered by status
- All classes must use package `org.howard.edu.lsp.midterm.crccards`
- All classes and public methods must include Javadoc comments with `@param` and `@return` where applicable

---

**Claude:**
Here is the implementation plan:

- `Task.java`: Fields `taskId`, `description`, `status`. Constructor sets default status to `"OPEN"`. `setStatus()` validates against the three valid values (case-sensitive) and sets `"UNKNOWN"` for anything else. `toString()` returns `taskId + " " + description + " [" + status + "]"`.
- `TaskManager.java`: Uses a `LinkedHashMap<String, Task>` (keyed by taskId) to preserve insertion order and allow O(1) lookup. `addTask()` checks for duplicate IDs and throws `IllegalArgumentException`. `findTask()` returns the task or `null`. `getTasksByStatus()` iterates and filters by status.

**Task.java:**
```java
package org.howard.edu.lsp.midterm.crccards;

/**
 * Represents a task in the Task Management System.
 * Stores task information including ID, description, and status,
 * and provides methods to update and retrieve task details.
 *
 * @author Aayush Bashyal
 */
public class Task {

    private String taskId;
    private String description;
    private String status;

    /**
     * Constructs a new Task with the given ID and description.
     * The default status is set to "OPEN".
     *
     * @param taskId      the unique identifier for the task
     * @param description a brief description of the task
     */
    public Task(String taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.status = "OPEN";
    }

    /** @return the task ID */
    public String getTaskId() { return taskId; }

    /** @return the task description */
    public String getDescription() { return description; }

    /** @return the current task status */
    public String getStatus() { return status; }

    /**
     * Sets the status of the task. Valid values are "OPEN", "IN_PROGRESS",
     * and "COMPLETE" (case-sensitive). Any other value sets status to "UNKNOWN".
     *
     * @param status the new status to assign
     */
    public void setStatus(String status) {
        if (status.equals("OPEN") || status.equals("IN_PROGRESS") || status.equals("COMPLETE")) {
            this.status = status;
        } else {
            this.status = "UNKNOWN";
        }
    }

    /**
     * Returns a string representation in the format: taskId description [status]
     *
     * @return formatted string representation of the task
     */
    @Override
    public String toString() {
        return taskId + " " + description + " [" + status + "]";
    }
}
```

**TaskManager.java:**
```java
package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a collection of Task objects in the Task Management System.
 * Provides functionality to add tasks, find tasks by ID,
 * and retrieve tasks filtered by status.
 *
 * @author Aayush Bashyal
 */
public class TaskManager {

    private Map<String, Task> tasks;

    /** Constructs a new TaskManager with an empty task collection. */
    public TaskManager() {
        tasks = new LinkedHashMap<>();
    }

    /**
     * Adds a new task to the manager.
     *
     * @param task the Task object to add
     * @throws IllegalArgumentException if a task with the same ID already exists
     */
    public void addTask(Task task) {
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException("Duplicate task ID: " + task.getTaskId());
        }
        tasks.put(task.getTaskId(), task);
    }

    /**
     * Finds and returns a task by its ID, or null if not found.
     *
     * @param taskId the ID of the task to find
     * @return the Task with the given ID, or null if not found
     */
    public Task findTask(String taskId) {
        return tasks.getOrDefault(taskId, null);
    }

    /**
     * Returns a list of all tasks with the given status.
     *
     * @param status the status to filter by
     * @return a List of Task objects matching the given status
     */
    public List<Task> getTasksByStatus(String status) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getStatus().equals(status)) {
                result.add(task);
            }
        }
        return result;
    }
}
```

After running the provided Driver class, the output matched the expected output exactly:

```
OPEN TASKS
T1 Write report [OPEN]
T3 Submit homework [OPEN]

TESTING DUPLICATE TASK ID
Duplicate task ID detected.

TESTING INVALID STATUS
T4 Invalid status test [UNKNOWN]

TESTING FIND TASK
T2 Study for exam [IN_PROGRESS]
Task not found.
```
