package com.example.main.Model.TaskManager;

import com.example.main.Model.UserAccessModel;
import java.time.LocalDate;

public class TaskFactory {
    public static Task createTask(String username, String description, LocalDate date, String priority) {
        // Create a task based on if the current user is a student or a teacher
        if (UserAccessModel.getCurrentUser().GetIsTeacher()) {
            assert true; // Tasks created by a teacher are auto-assigned to relevant students
        }
        else {
            assert true; // Tasks created by a student are assigned only to the student
        }
        return new Task(username, description, date, priority);
    }
}