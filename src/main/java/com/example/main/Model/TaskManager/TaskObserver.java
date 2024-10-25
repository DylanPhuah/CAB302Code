package com.example.main.Model.TaskManager;

public interface TaskObserver {
    void onTaskAdded(Task task);
    void onTaskRemoved(Task task);
}