package com.example.oopguitest;
import java.util.Random;


public abstract class User {
    private String name;
    private int userID;
    private String password;

    public User(String Name, String Password)
    {
        name = Name;
        password = Password;

        //Generate a random 10 character long integer to use as user ID. Ultimately we will want to check against database if the ID can be used.
        Random random  = new Random();
        int min = (int) Math.pow(10, 10 - 1);
        int max = (int) Math.pow(10, 10) - 1;
        userID=  random.nextInt(max - min + 1) + min;

    }

}
