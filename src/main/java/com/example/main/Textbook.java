package com.example.main;

public class Textbook {
    private String title;
    private String unitCode;
    private String text;

    public String GetTitle() {
        return title;
    }

    public String GetUnitCode() {
        return unitCode;
    }

    public String GetText() {
        return text;
    }

    public Textbook(String Title, String UnitCode, String Text) {
        title = Title;
        unitCode = UnitCode;
        text = Text;
    }
}
