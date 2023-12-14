package br.ufrn.imd.logic;

import br.ufrn.imd.filehandling.PlaylistFileHandler;
import br.ufrn.imd.exceptions.PlaylistException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AudioPlayer {
    private AdvancedPlayer player;
    private ArrayList<String> playlist;

    
    public AudioPlayer(String filePath, boolean isPlaylist) {
        try {
            if (isPlaylist) {
                this.playlist = loadPlaylist(filePath);
                if (!this.playlist.isEmpty()) {
                    FileInputStream fileInputStream = new FileInputStream(this.playlist.get(0));
                    this.player = new AdvancedPlayer(fileInputStream);
                }
            } else {
                FileInputStream fileInputStream = new FileInputStream(filePath);
                player = new AdvancedPlayer(fileInputStream);
            }
        } catch (JavaLayerException | IOException | PlaylistException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> loadPlaylist(String playlistFilePath) throws PlaylistException {
        try {
            PlaylistFileHandler playlistFileHandler = new PlaylistFileHandler(playlistFilePath);
            return playlistFileHandler.readData();
        } catch (PlaylistException e) {
            throw new PlaylistException("Error loading playlist.", e);
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
        if (player != null && playlist != null && !playlist.isEmpty()) {
            // Start the playlist player in a new thread
            Thread playlistThread = new Thread(() -> {
                try {
                    for (String filePath : playlist) {
                        FileInputStream fileInputStream = new FileInputStream(filePath);
                        player = new AdvancedPlayer(fileInputStream);
                        player.play();
                    }
                } catch (JavaLayerException | IOException e) {
                    e.printStackTrace();
                }
            });
            playlistThread.start();
        }
    }

    public void stop() {
        if (player != null) {
            player.close();
        }
    }
}
