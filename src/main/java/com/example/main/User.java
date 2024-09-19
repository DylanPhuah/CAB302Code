package com.example.main;

/** A user with a registered account on the UniPlus platform, and their associated data */
public class User {
    /** The user's chosen username */
    private String username;
    /** The user's chosen password */
    private String password;
    /** The user's given (first) name */
    private String fName;
    /** The user's sur- (last) name */
    private String lName;
    /** Whether the user is a teacher, false for student users */
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
     * Instantiates a user with the given data
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
