package com.example.main.Model;

import javax.swing.JOptionPane;
/** Used by controllers to display error messages*/

public class ExceptionPopUp
{
    public static void exceptionPopUp(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "Error: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}