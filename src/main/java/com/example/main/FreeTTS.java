package com.example.main;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.AudioException;

public class FreeTTS {
    public FreeTTS(String text) {
        Voice voice = null;
        try {
            // Set system property for voice directory
            try {
                System.setProperty("freetts.voices",
                        "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            } catch (SecurityException e) {
                System.out.println("SecurityException while setting system property: " + e.getMessage());
                e.printStackTrace();
            }

            // Get the VoiceManager instance
            VoiceManager vm = null;
            try {
                vm = VoiceManager.getInstance();
            } catch (Exception e) {
                System.out.println("Exception while getting VoiceManager instance: " + e.getMessage());
                e.printStackTrace();
                return;
            }

            if (vm == null) {
                throw new IllegalStateException("Cannot get VoiceManager instance.");
            }

            // Get the voice named "kevin16"
            try {
                voice = vm.getVoice("kevin16");
            } catch (Exception e) {
                System.out.println("Exception while getting voice: " + e.getMessage());
                e.printStackTrace();
                return;
            }

            if (voice == null) {
                throw new IllegalStateException("Voice 'kevin16' not found.");
            }

            // Allocate the voice
            try {
                voice.allocate();
            } catch (EngineStateError e) {
                System.out.println("EngineStateError during voice allocation: " + e.getMessage());
                e.printStackTrace();
                return;
            }

            // Speak the text
            try {
                voice.speak(text);
            } catch (EngineStateError e) {
                System.out.println("EngineStateError during speaking: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid argument: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalStateException e) {
            System.out.println("Illegal state: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Deallocate the voice
            if (voice != null) {
                try {
                    voice.deallocate();
                } catch (EngineStateError e) {
                    System.out.println("EngineStateError during deallocation: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
