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

    /**
     * Constructs a new TaskManager with an empty task collection.
     */
    public TaskManager() {
        tasks = new LinkedHashMap<>();
    }

    /**
     * Adds a new task to the manager.
     * Throws an IllegalArgumentException if a task with the same ID already exists.
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
     * Finds and returns a task by its ID.
     * Returns null if no task with the given ID exists.
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
     * @param status the status to filter tasks by (e.g., "OPEN", "IN_PROGRESS", "COMPLETE")
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
