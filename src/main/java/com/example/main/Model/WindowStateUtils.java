package com.example.main.Model;

import javafx.stage.Stage;
import javafx.stage.Window;

public class WindowStateUtils {

    public static double minLoginWidth = 625;
    public static double minLoginHeight = 353;
    public static double minMainWidth = 1220;
    public static double minMainHeight = 855;

    public static class WindowState {
        public boolean maximised;
        public boolean fullScreen;
        public double width;
        public double height;
    }

    public static WindowState captureWindowState(Stage stage) {
        WindowState state = new WindowState ();
        state.maximised = stage.isMaximized();
        state.fullScreen = stage.isFullScreen();
        state.width = stage.getWidth();
        state.height = stage.getHeight();
        return state;
    }

    public static void restoreWindowState(Stage stage, WindowState state) {
        stage.setMaximized(state.maximised);
        stage.setFullScreen(state.fullScreen);
        if (!state.maximised && !state.fullScreen) {
            stage.setWidth(state.width);
            stage.setHeight(state.height);
        }
    }
}
