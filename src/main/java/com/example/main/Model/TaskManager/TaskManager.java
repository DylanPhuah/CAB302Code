package com.example.main.Model.TaskManager;

import com.example.main.Model.DAO.TaskDAO;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static TaskManager instance;
    private List<Task> tasks;
    private List<TaskObserver> observers;

    public TaskManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TaskObserver observer) {
        observers.remove(observer);
    }

    public void addTask(Task task) {
        tasks.add(task);
        TaskDAO taskDAO = new TaskDAO();
        taskDAO.insert(task);
        notifyObservers(task, true);
    }

    public void removeTask(String description) {
        Task taskToRemove = null;
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                taskToRemove = task;
                break;
            }
        }
        if (taskToRemove != null) {
            tasks.remove(taskToRemove);
            notifyObservers(taskToRemove, false);
        }
    }

    private void notifyObservers(Task task, boolean added) {
        for (TaskObserver observer : observers) {
            if (added) {
                observer.onTaskAdded(task);
            } else {
                observer.onTaskRemoved(task);
            }
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }
}