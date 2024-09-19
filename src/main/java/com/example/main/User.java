package com.example.main;

/**
 * A simple object to represent a user and their data
 */
public class User {
    private String username;
    private String password;
    private String fName;
    private String lName;
    private Boolean isTeacher;

    public String GetUsername() {
        return username;
    }

    public String GetPassword() {
        return password;
    }

    public String GetFName() {
        return fName;
    }

    public String GetLName() {
        return lName;
    }

    public Boolean GetIsTeacher() {
        return isTeacher;
    }

    /**
     *
     * @param Username The username chosen by the user
     * @param Password The password chosen by the user
     * @param FName The first-name provided by the user
     * @param LName The last-name provided by the user
     * @param IsTeacher The status of the account, true for teacher or false for student
     */
    public User(String Username, String Password, String FName, String LName, Boolean IsTeacher) {
        username = Username;
        password = Password;
        fName = FName;
        lName = LName;
        isTeacher = IsTeacher;
    }
}
