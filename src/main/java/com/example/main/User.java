package com.example.main;

/**
 * A simple object to represent a user and their data
 */
public class User {
    public String username;
    public String password;
    public String fName;
    public String lName;
    public Boolean isTeacher;

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
