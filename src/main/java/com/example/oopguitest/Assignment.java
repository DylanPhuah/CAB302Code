package com.example.oopguitest;

import java.util.ArrayList;

public class Assignment {

    private String assignmentName;
    private String assignmentDueDate;
    private Textbook assignmentTextbook;
    private Teacher creator;
    private ArrayList<Student> studentsInProgress;
    private ArrayList<Student> studentsComplete;
    private int pageNumbers;

    public Assignment(String assignmentName, String assignmentDueDate, Textbook assignmentTextbook, Teacher creator, int pageNumbers) {
        this.assignmentName = assignmentName;
        this.pageNumbers = pageNumbers;
        this.assignmentDueDate = assignmentDueDate;
        this.assignmentTextbook = assignmentTextbook;
        this.creator = creator;
        this.studentsComplete = new ArrayList<Student>();;
        this.studentsInProgress = new ArrayList<Student>();
    }
}
