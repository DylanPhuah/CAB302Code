package com.example.main;

/** A textbook uploaded to the UniPlus platform */
public class Textbook {
    /** The title of the textbook within UniPlus */
    private String title;
    /** The unit code of the unit the textbook belongs to */
    private String unitCode;
    /** The text content of the textbook */
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

    /**
     * Instantiates a textbook with the given data
     * @param Title The title of the textbook within UniPlus
     * @param UnitCode The unit code of the unit the textbook is uploaded to
     * @param Text The text content of the textbook
     */
    public Textbook(String Title, String UnitCode, String Text) {
        title = Title;
        unitCode = UnitCode;
        text = Text;
    }

    @Override
    public String toString() {
        return title + "for unit" + unitCode;
    }
}

