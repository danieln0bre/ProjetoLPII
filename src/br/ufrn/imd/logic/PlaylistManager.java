package br.ufrn.imd.logic;

import br.ufrn.imd.filehandling.PlaylistFileHandler;
import br.ufrn.imd.authentication.UserManager;

import java.io.File;
import java.util.ArrayList;


public class PlaylistManager {

    private static final String PLAYLIST_DIRECTORY_BASE = "./files/";

    public PlaylistManager() {
        // Construtor vazio
    }

    public void createPlaylist(String username, String playlistName) {
        // Verificar se o usuário é VIP antes de criar a playlist
        UserManager userManager = new UserManager();

        if (userManager.isUserVip(username)) {
            String playlistDirectoryPath = PLAYLIST_DIRECTORY_BASE + username + "/playlists/";
            String playlistFilePath = playlistDirectoryPath + "playlist_" + playlistName + ".txt";

            createDirectoryIfNotExists(playlistDirectoryPath);

            PlaylistFileHandler playlistFileHandler = new PlaylistFileHandler(playlistFilePath);

            // Exemplo de escrita em um arquivo de playlist
            ArrayList<String> playlistData = new ArrayList<>();
            playlistData.add("Song 1");
            playlistData.add("Song 2");
            playlistFileHandler.writeData(playlistData);

            System.out.println("Playlist created: " + playlistFilePath);
        } else {
            System.err.println("Error: Only VIP users can create playlists.");
        }
    }

    public void addSongToPlaylist(String username, String playlistName, String newSong) {
        String playlistDirectoryPath = PLAYLIST_DIRECTORY_BASE + username + "/playlists/";
        String playlistFilePath = playlistDirectoryPath + "playlist_" + playlistName + ".txt";

        PlaylistFileHandler playlistFileHandler = new PlaylistFileHandler(playlistFilePath);

        // Exemplo de leitura de um arquivo de playlist
        ArrayList<String> playlistData = playlistFileHandler.readData();

        // Adiciona a nova música
        playlistData.add(newSong);

        // Sobrescreve o arquivo de playlist com a música adicionada
        playlistFileHandler.writeData(playlistData);

        System.out.println("Song added to playlist: " + newSong);
    }

    public void removeSongFromPlaylist(String username, String playlistName, String songToRemove) {
        String playlistDirectoryPath = PLAYLIST_DIRECTORY_BASE + username + "/playlists/";
        String playlistFilePath = playlistDirectoryPath + "playlist_" + playlistName + ".txt";

        PlaylistFileHandler playlistFileHandler = new PlaylistFileHandler(playlistFilePath);

        // Exemplo de leitura de um arquivo de playlist
        ArrayList<String> playlistData = playlistFileHandler.readData();

        // Remove a música da playlist
        if (playlistData.remove(songToRemove)) {
            // Sobrescreve o arquivo de playlist sem a música removida
            playlistFileHandler.writeData(playlistData);
            System.out.println("Song removed from playlist: " + songToRemove);
        } else {
            System.out.println("Song not found in playlist: " + songToRemove);
        }
    }

    public void deletePlaylist(String username, String playlistName) {
        String playlistDirectoryPath = PLAYLIST_DIRECTORY_BASE + username + "/playlists/";
        String playlistFilePath = playlistDirectoryPath + "playlist_" + playlistName + ".txt";

        File playlistFile = new File(playlistFilePath);
        if (playlistFile.exists()) {
            if (playlistFile.delete()) {
                System.out.println("Playlist deleted: " + playlistFilePath);
            } else {
                System.err.println("Failed to delete playlist: " + playlistFilePath);
            }
        } else {
            System.err.println("Error: Playlist does not exist.");
        }
    }

    private void createDirectoryIfNotExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + directoryPath);
            } else {
                System.err.println("Failed to create directory: " + directoryPath);
            }
        }
    }
}
