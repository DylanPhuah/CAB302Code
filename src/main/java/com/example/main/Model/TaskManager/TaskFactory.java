package com.example.main.Model.TaskManager;

import java.time.LocalDate;

public class TaskFactory {
    public static Task createTask(String description, LocalDate date, String priority) {
        return new Task(description, date, priority);
    }
}