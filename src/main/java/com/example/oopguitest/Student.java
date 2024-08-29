package com.example.oopguitest;

import java.util.ArrayList;

public class Student extends User {
    private ArrayList<Unit> unitsEnrolled;

    public Student(String Name, String Password) {
        super(Name, Password);
        unitsEnrolled = new ArrayList<Unit>();
    }

    private void completeAssignment(){}
}
