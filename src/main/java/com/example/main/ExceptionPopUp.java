package com.example.main;

import javax.swing.JOptionPane;

public class ExceptionPopUp
{

    public static void exceptionPopUp(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "Error" + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}