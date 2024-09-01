package com.example.main;

public class User {
    public String username;
    public String password;
    public String fName;
    public String lName;
    public Boolean isTeacher;

    public User(String Username, String Password, String FName, String LName, Boolean IsTeacher) {
        username = Username;
        password = Password;
        fName = FName;
        lName = LName;
        isTeacher = IsTeacher;
    }
}
