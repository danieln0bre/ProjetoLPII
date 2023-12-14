package br.ufrn.imd.logic;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import br.ufrn.imd.filehandling.PlaylistFileHandler;
import br.ufrn.imd.exceptions.PlaylistException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AudioPlayer {
    private AdvancedPlayer player;
    private PlaylistFileHandler playlistFileHandler;

    public AudioPlayer(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            player = new AdvancedPlayer(fileInputStream);
            playlistFileHandler = new PlaylistFileHandler(filePath);
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

    public void playPlaylist() {
        // Method to play the songs from the playlist
        Thread playlistThread = new Thread(() -> {
            try {
                ArrayList<String> playlist = playlistFileHandler.readData();
                for (String track : playlist) {
                    playTrack(track);
                }
            } catch (PlaylistException e) {
                e.printStackTrace();
            }
        });
        playlistThread.start();
    }

    private void playTrack(String trackPath) {
        try {
            player.close();
            player = new AdvancedPlayer(new FileInputStream(trackPath));
            player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent evt) {
                    // You can add logic here if needed
                }
            });
            play();
        } catch (JavaLayerException | IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (player != null) {
            player.close();
        }
    }
}
