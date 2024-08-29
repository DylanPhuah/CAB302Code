package com.example.oopguitest;

import java.util.Random;

public class Textbook {

    private String textbookName;
    private int textbookID;
    private String textbookContent;

    public Textbook(String textbookName, String textbookContent) {
        this.textbookName = textbookName;
        this.textbookContent = textbookContent;


        Random random  = new Random(); //Implement checking and generation for a unique textbook ID that isnt taken in the database
        int min = (int) Math.pow(10, 10 - 1);
        int max = (int) Math.pow(10, 10) - 1;
        textbookID=  random.nextInt(max - min + 1) + min;
    }
}
