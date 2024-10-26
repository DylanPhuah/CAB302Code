package com.example.main.Model.TaskManager;

import com.example.main.Model.DAO.TaskDAO;
import com.example.main.Model.UserAccessModel;

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
        TaskDAO taskDAO = new TaskDAO();
        List<Task> allTasks = taskDAO.getTasksByUser(UserAccessModel.getCurrentUser());
        notifyObservers(task, true);
        tasks.add(task);
        // Check for uniqueness
        boolean found = allTasks.stream().anyMatch(t ->
                t.getDescription().equals(task.getDescription()) &&
                        t.getDate().equals(task.getDate()) &&
                        t.getPriority().equals(task.getPriority())
        );

        if (!found) {
            taskDAO.insert(task);
            System.out.println("Added Task: " + task.getDescription());
        }
    }

    public void removeTask(String description) {
        Task taskToRemove = null;
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                taskToRemove = task;
                TaskDAO taskDAO = new TaskDAO();
                taskDAO.delete(task);
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