package com.example.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class UserAcsessModel {

    private static HashMap<Enrolment,List<Textbook>> UnitTextBooks;
    private static Textbook requested = null;
    private static User currentUser = null;
    public static Boolean displayTeacher = false;

    public static HashMap<Enrolment, List<Textbook>> getUnitTextBooks() {
        return UnitTextBooks;
    }

    public static void RequestTextBookView(Textbook textbook)
    {
        requested = textbook;
    }

    public static void SetDisplayTeacher(Boolean input) {
        displayTeacher = input;
    }

    public static boolean getdisplayTeacher() {
        return displayTeacher;
    }

    public static Textbook getRequested()
    {
        return requested;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setUser(User user)
    {
        currentUser = user;
        UnitTextBooks = new HashMap<Enrolment,List<Textbook>>();
        UserDAO userDAO = new UserDAO();
        EnrolmentDAO enrolmentDAO = new EnrolmentDAO();
        TextbookDAO textbookDAO = new TextbookDAO();
        List<Enrolment> Enrolments = enrolmentDAO.getAllByUser(user.GetUsername());
        for (Enrolment enrolment : Enrolments)
        {
            List<Textbook> books = textbookDAO.getAllByUnit(enrolment.GetUnitCode());
            UnitTextBooks.put(enrolment,books);
        }
    }

    public static void setTextPreference(int newPreference) {
        currentUser.SetTextPreference(newPreference);
    }
}
