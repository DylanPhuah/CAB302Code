package com.example.main;

public class Enrolment {
    private String username;
    private String unitCode;

    public String GetUsername() {
        return username;
    }

    public String GetUnitCode() {
        return unitCode;
    }

    public Enrolment(String Username, String UnitCode) {
        username = Username;
        unitCode = UnitCode;
    }
}
