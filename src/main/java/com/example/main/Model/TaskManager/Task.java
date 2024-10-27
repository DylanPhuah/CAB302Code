package com.example.main.Model.TaskManager;

import java.time.LocalDate;

public class Task {
    private String username;
    private String description;
    private LocalDate date;
    private String priority;


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