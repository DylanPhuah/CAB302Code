package com.example.oopguitest;

import java.util.ArrayList;

public class Teacher extends User {
    private ArrayList<Unit> unitsTaught;

    public Teacher(String Name, String Password)
    {
        super(Name,Password);
        unitsTaught = new ArrayList<Unit>();
    }

    private void makeNewUnit(String name,ArrayList<Student> students)
    {
        Unit newUnit = new Unit(name,this,students);
        unitsTaught.add((newUnit));
    }

    private void makeAssignment(String name, String dueDate, Textbook book, int pageNumber,Unit unit)
    {
        Assignment newAssignment = new Assignment(name,dueDate,book,this,pageNumber,unit);
        unit.AddAssignment(newAssignment);
    }

    private void addTextbook(Unit unit, String name, String content)
    {
        Textbook newTextbook = new Textbook(name, content);
        unit.addTextbook(newTextbook);
    }



}
