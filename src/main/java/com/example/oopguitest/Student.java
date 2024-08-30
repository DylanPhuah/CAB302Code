package com.example.oopguitest;

import java.util.ArrayList;

public class Student extends User {
    private ArrayList<Unit> unitsEnrolled;
    private ArrayList<Assignment> assignments;


    public Student(String Name, String Password) {
        super(Name, Password);
        unitsEnrolled = new ArrayList<Unit>();
    }

    public ArrayList<Assignment> getAssignments()
    {
        return this.assignments;
    }

    private ArrayList<Assignment> getAllIncompleteAssignments()
    {
        ArrayList<Assignment> output = new ArrayList<Assignment>();
        for (Assignment assignment : this.assignments)
        {
            if(assignment.isComplete())
            {
                output.add(assignment);
            }
        }
        return output;
    }

    public void addAssignment(Assignment assignment)
    {
        this.assignments.add(assignment);
    }

    private void progressAssignment(Assignment assignment)
    {
        if(!assignment.readPage())
        {
            System.out.println("Assignment complete!");
        }
    }
}
