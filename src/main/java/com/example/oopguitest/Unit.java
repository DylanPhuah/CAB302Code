package com.example.oopguitest;

import java.util.ArrayList;
import java.util.Random;

public class Unit {
    private String unitName;
    private int unitID;
    private Teacher unitCoordinator;
    private ArrayList<Student> studentsEnrolled;
    private ArrayList<Textbook> unitTextbooks;
    private ArrayList<Textbook> unitAssignments;


    public Unit(String unitName, Teacher unitCoordinator) {
        this.unitName = unitName;
        this.unitCoordinator = unitCoordinator;
        this.studentsEnrolled = new ArrayList<Student>();

        Random random  = new Random(); //Implement checking and generation for a unique unit ID that isnt taken in the database
        int min = (int) Math.pow(10, 10 - 1);
        int max = (int) Math.pow(10, 10) - 1;
        unitID=  random.nextInt(max - min + 1) + min;
    }

    public Teacher getUnitCoordinator() {
        return unitCoordinator;
    }

    public ArrayList<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public ArrayList<Textbook> getUnitTextbooks() {
        return unitTextbooks;
    }

    public ArrayList<Textbook> getUnitAssignments() {
        return unitAssignments;
    }
}
