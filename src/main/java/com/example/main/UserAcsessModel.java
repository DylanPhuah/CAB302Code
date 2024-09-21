package com.example.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class UserAcsessModel {
    private static UserAcsessModel instance = null;

    public HashMap<Enrolment, List<Textbook>> getUnitTextBooks() {
        return UnitTextBooks;
    }

    private  HashMap<Enrolment,List<Textbook>> UnitTextBooks;
    private Textbook requested = null;

    public UserAcsessModel(User user) {
        if(instance != null)
        {
            System.err.println("User acsess model already initialised!");
        }
        instance = this;
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

    public void RequestTextBookView(Textbook textbook)
    {
        requested = textbook;
    }

    public Textbook getRequested() {
        return requested;
    }

    public static UserAcsessModel getInstance() {
        if (instance == null) {
            System.err.println("User acsess model not initialised!");
        }
        return instance;
    }
}
