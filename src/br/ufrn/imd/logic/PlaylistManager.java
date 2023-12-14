package br.ufrn.imd.logic;

import br.ufrn.imd.exceptions.PlaylistException;
import br.ufrn.imd.filehandling.PlaylistFileHandler;
import br.ufrn.imd.authentication.User;
import br.ufrn.imd.authentication.UserManager;

import java.io.File;
import java.util.ArrayList;

public class PlaylistManager {

    private static final String PLAYLIST_DIRECTORY_BASE = "./files/";

    public PlaylistManager() {
        // Construtor vazio
    }

    public void createPlaylist(User user, String playlistName) {
        try {
            // Verificar se o usuário é VIP antes de criar a playlist
            UserManager userManager = new UserManager();

            if (userManager.isUserVip(user)) {
                String playlistDirectoryPath = PLAYLIST_DIRECTORY_BASE + user.getUsername() + "/playlists/";
                String playlistFilePath = playlistDirectoryPath + "playlist_" + playlistName + ".txt";

                createDirectoryIfNotExists(playlistDirectoryPath);

                PlaylistFileHandler playlistFileHandler = new PlaylistFileHandler(playlistFilePath);

                // Exemplo de escrita em um arquivo de playlist
                ArrayList<String> playlistData = new ArrayList<>();
                playlistFileHandler.writeData(playlistData);

                System.out.println("Playlist created: " + playlistFilePath);
            } else {
                System.err.println("Error: Only VIP users can create playlists.");
            }
        } catch (PlaylistException e) {
            System.err.println("Error creating playlist: " + e.getMessage());
        }
    }

    public void addSongToPlaylist(User user, String playlistName, String newSong) {
        try {
            String playlistDirectoryPath = PLAYLIST_DIRECTORY_BASE + user.getUsername() + "/playlists/";
            String playlistFilePath = playlistDirectoryPath + "playlist_" + playlistName + ".txt";

            PlaylistFileHandler playlistFileHandler = new PlaylistFileHandler(playlistFilePath);

            // Exemplo de leitura de um arquivo de playlist
            ArrayList<String> playlistData = playlistFileHandler.readData();

            // Adiciona a nova música
            playlistData.add(newSong);

            // Sobrescreve o arquivo de playlist com a música adicionada
            playlistFileHandler.writeData(playlistData);

            System.out.println("Song added to playlist: " + newSong);
        } catch (PlaylistException e) {
            System.err.println("Error adding song to playlist: " + e.getMessage());
        }
    }

    public void removeSongFromPlaylist(User user, String playlistName, String songToRemove) {
        try {
            String playlistDirectoryPath = PLAYLIST_DIRECTORY_BASE + user.getUsername() + "/playlists/";
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
        } catch (PlaylistException e) {
            System.err.println("Error removing song from playlist: " + e.getMessage());
        }
    }

    public void deletePlaylist(User user, String playlistName) {
    	System.out.println(playlistName);
        String playlistDirectoryPath = PLAYLIST_DIRECTORY_BASE + user.getUsername() + "/playlists/";
        System.out.println(user.getUsername());
        String playlistFilePath = playlistDirectoryPath + playlistName;
        System.out.println(playlistFilePath);

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

    private void createDirectoryIfNotExists(String directoryPath) throws PlaylistException {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + directoryPath);
            } else {
                throw new PlaylistException("Failed to create directory: " + directoryPath);
            }
        }
    }
    
    public ArrayList<String> loadUserPlaylists(User user) {
    	ArrayList<String> userPlaylists = new ArrayList<>();
        String userPlaylistDirectory = PLAYLIST_DIRECTORY_BASE + user.getUsername() + "/playlists/";

        File userDirectory = new File(userPlaylistDirectory);
        if (userDirectory.exists() && userDirectory.isDirectory()) {
            File[] playlistFiles = userDirectory.listFiles();
            if (playlistFiles != null) {
                for (File playlistFile : playlistFiles) {
                    userPlaylists.add(playlistFile.getName());
                }
            }
        }

        return userPlaylists;
    }
    
    public ArrayList<String> getPlaylistSongs(User user, String playlistName) {
    	ArrayList<String> playlistSongs = new ArrayList<>();

        try {
            String playlistDirectoryPath = PLAYLIST_DIRECTORY_BASE + user.getUsername() + "/playlists/";
            String playlistFilePath = playlistDirectoryPath + "playlist_" + playlistName + ".txt";

            PlaylistFileHandler playlistFileHandler = new PlaylistFileHandler(playlistFilePath);

            // Exemplo de leitura de um arquivo de playlist
            ArrayList<String> playlistData = playlistFileHandler.readData();

            // Adiciona as músicas da playlist à lista
            playlistSongs.addAll(playlistData);
        } catch (PlaylistException e) {
            System.err.println("Error getting playlist songs: " + e.getMessage());
        }

        return playlistSongs;
    }
    
    
}
