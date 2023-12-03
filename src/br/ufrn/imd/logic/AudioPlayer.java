package br.ufrn.imd.logic;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.IOException;

public class AudioPlayer {
    private AdvancedPlayer player;

    public AudioPlayer(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            player = new AdvancedPlayer(fileInputStream);
        } catch (JavaLayerException | IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (player != null) {
            // Start the player in a new thread
            Thread playerThread = new Thread(() -> {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            });
            playerThread.start();
        }
    }

    public void stop() {
        if (player != null) {
            player.close();
        }
    }
}
    