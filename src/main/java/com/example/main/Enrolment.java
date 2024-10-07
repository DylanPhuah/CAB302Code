package com.example.main;

/** An enrolment on the UniPlus platform, formed by a user associated with a unit */
public class Enrolment {
    /** The username of the associated user */
    private String username;
    /** The unit code of the associated unit */
    private String unitCode;

    public String GetUsername() {
        return username;
    }

    public String GetUnitCode() {
        return unitCode;
    }

    /**
     * Instantiates an enrolment with the given data
     * @param Username The username of the associated user
     * @param UnitCode The unit code of the associated unit
     */
    public Enrolment(String Username, String UnitCode) {
        username = Username;
        unitCode = UnitCode;
    }

    @Override
    public String toString() {
        return username + "In unit " + unitCode;
    }
}
