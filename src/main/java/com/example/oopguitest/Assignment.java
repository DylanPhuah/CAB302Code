package com.example.oopguitest;

import java.util.ArrayList;

public class Assignment {

    private String assignmentName;
    private String assignmentDueDate;
    private Textbook assignmentTextbook;
    private Teacher creator;
    private Unit Unit;
    private ArrayList<Student> studentsInProgress;
    private ArrayList<Student> studentsComplete;
    private int pageNumbers;
    private int pageNumbersRead;

    public boolean isComplete() {
        return complete;
    }
    public boolean readPage()
    {
        if(this.pageNumbersRead != this.pageNumbers)
        {
            this.pageNumbersRead++;
            return true;
        }
        else
        {
            this.complete = true;
            return false;
        }

    }

    private boolean complete;

    public Assignment(String assignmentName, String assignmentDueDate, Textbook assignmentTextbook, Teacher creator, int pageNumbers, Unit unit) {
        this.assignmentName = assignmentName;
        this.Unit = Unit;
        this.pageNumbers = pageNumbers;
        this.assignmentDueDate = assignmentDueDate;
        this.assignmentTextbook = assignmentTextbook;
        this.creator = creator;
        this.studentsComplete = new ArrayList<Student>();;
        this.studentsInProgress = new ArrayList<Student>();
        this.complete = false;
        this.pageNumbersRead = 0;
    }


}
