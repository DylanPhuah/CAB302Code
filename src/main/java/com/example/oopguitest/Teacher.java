package com.example.oopguitest;

import java.util.ArrayList;

public class Teacher extends User {
    private ArrayList<Unit> unitsTaught;

    public Teacher(String Name, String Password)
    {
        super(Name,Password);
        unitsTaught = new ArrayList<Unit>();
    }


    private void makeNewUnit(){}

    private void makeAssignment(){}

    private void addTextbook(){}

    private void assignAssignment(){}


}
