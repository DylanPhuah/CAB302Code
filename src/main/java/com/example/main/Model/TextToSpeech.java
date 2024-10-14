package com.example.main.Model;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javax.speech.EngineStateError;

public class TextToSpeech {
    private Voice voice;
    private Thread speechThread;
    private volatile boolean isSpeaking;

    public TextToSpeech(String text) {
        // Initialize the voice in the constructor
        initializeVoice();

        // Start the speech in a separate thread
        startSpeechThread(text);

    }

    private void initializeVoice() {
        try {
            System.setProperty("freetts.voices",
                    "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

            VoiceManager vm = VoiceManager.getInstance();
            if (vm == null) {
                throw new IllegalStateException("Cannot get VoiceManager instance.");
            }

            voice = vm.getVoice("kevin16");
            if (voice == null) {
                throw new IllegalStateException("Voice 'kevin16' not found.");
            }

            voice.allocate();
        } catch (Exception e) {
            System.out.println("Error initializing voice: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void startSpeechThread(String text) {
        isSpeaking = true;

        speechThread = new Thread(() -> {
            try {
                voice.speak(text);
            } catch (EngineStateError e) {
                System.out.println("EngineStateError during speaking: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred during speaking: " + e.getMessage());
                e.printStackTrace();
            } finally {
                isSpeaking = false;
                voice.deallocate();
            }
        });

        speechThread.setDaemon(true);

        speechThread.start();
    }

    // Method to stop speaking when leaving the scene
    public void stopSpeaking() {
        if (isSpeaking && voice != null) {
            try {
                // Stop the speech
                voice.getAudioPlayer().cancel();
                // Deallocate the voice
                voice.deallocate();
                isSpeaking = false;
            } catch (EngineStateError e) {
                System.out.println("EngineStateError during deallocation: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred during stopSpeaking: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}