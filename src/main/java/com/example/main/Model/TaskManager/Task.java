package com.example.main.Model.TaskManager;

import java.time.LocalDate;

public class Task {

    private String description;
    private LocalDate date;
    private String priority;


    public Task(String description, LocalDate date, String priority) {

        this.description = description;
        this.date = date;
        this.priority = priority;
    }
    // Getters for encapsulation
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