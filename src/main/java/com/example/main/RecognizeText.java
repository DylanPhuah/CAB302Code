package com.example.main;


import net.sourceforge.tess4j.*;
import org.opencv.core.*;
import java.io.*;

import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.*;


public class RecognizeText {
    static String SRC_PATH = "C:/java_text/";
    static Tesseract tesseract = new Tesseract();
    static{
        // System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        nu.pattern.OpenCV.loadLocally();
        tesseract.setDatapath("C:/Users/Lachlan/Downloads/Tess4J-3.4.8-src/Tess4J/tessdata");
    }

    String extractString(Mat inputMat) {
        String result = "";
        Mat gray = new Mat();
        cvtColor(inputMat, gray, COLOR_BGR2GRAY);
        imwrite(SRC_PATH + "gray.png", gray);
        try {
            result = tesseract.doOCR(new File(SRC_PATH + "gray.png"));
        } catch (TesseractException e){
            e.printStackTrace();
        }

        return result;
    }
//temp method for testing
    String tempMethod(){
        Mat origin = imread(SRC_PATH + "2.png");
        return new RecognizeText().extractString(origin);
    }

}
