package com.example.main.Model.TaskManager;

import java.time.LocalDate;
/** A task is a tool for users to track things they need to do. Users can create one graphically, and they are stored in their accounts*/
public class Task {
    private String username;
    private String description;
    private LocalDate date;
    private String priority;

    /**
     * Instantiates a user with the given data
     * @param username The username chosen by the user
     * @param description The description of the task
     * @param date When the task is due
     * @param priority How important the task is
     */
    public Task(String username, String description, LocalDate date, String priority) {
        this.username = username;
        this.description = description;
        this.date = date;
        this.priority = priority;
    }
    // Getters for encapsulation
    public String getUsername() { return username; }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPriority() {
        return priority;
    }
}