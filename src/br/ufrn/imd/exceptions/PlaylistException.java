package br.ufrn.imd.exceptions;

public class PlaylistException extends MediaPlayerException {

    private static final long serialVersionUID = 3L;

    public PlaylistException(String message) {
        super(message);
    }

    public PlaylistException(String message, Throwable cause) {
        super(message, cause);
    }
}
