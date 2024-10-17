package com.example.main.Model;

import com.example.main.Model.DAO.EnrolmentDAO;
import com.example.main.Model.DAO.TextbookDAO;

import java.util.HashMap;
import java.util.List;

public class UserAccessModel {

    private static HashMap<Enrolment,List<Textbook>> UnitTextBooks;
    private static Textbook requested = null;
    private static User currentUser = null;
    private static Boolean displayTeacher = false;

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

    public static void updateUser(User user)
    {
        currentUser = user;
        UnitTextBooks = new HashMap<Enrolment,List<Textbook>>();
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
