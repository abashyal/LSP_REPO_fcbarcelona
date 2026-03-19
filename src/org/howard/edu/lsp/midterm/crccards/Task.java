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

    /**
     * Returns the unique ID of the task.
     *
     * @return the task ID
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the current status of the task.
     *
     * @return the task status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the task.
     * Valid values are "OPEN", "IN_PROGRESS", and "COMPLETE" (case-sensitive).
     * If an invalid value is provided, the status is set to "UNKNOWN".
     *
     * @param status the new status to assign to the task
     */
    public void setStatus(String status) {
        if (status.equals("OPEN") || status.equals("IN_PROGRESS") || status.equals("COMPLETE")) {
            this.status = status;
        } else {
            this.status = "UNKNOWN";
        }
    }

    /**
     * Returns a string representation of the task in the format:
     * taskId description [status]
     *
     * @return formatted string representation of the task
     */
    @Override
    public String toString() {
        return taskId + " " + description + " [" + status + "]";
    }
}
