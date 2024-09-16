package com.example.main;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTS {
    public static void main(String[] args) {
        // Set system property for voice directory
        System.setProperty("freetts.voices",
                "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        // Get the voice named "kevin16"
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");

        if (voice != null) {
            voice.allocate(); // Allocate resources for the voice
            voice.speak("Hello, this is a test using FreeTTS."); // Speak the text
            voice.deallocate(); // Deallocate when done
        } else {
            System.out.println("Voice not found.");
        }
    }
}
