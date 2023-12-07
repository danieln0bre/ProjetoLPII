package br.ufrn.imd.exceptions;

import java.io.Serializable;

public abstract class MediaPlayerException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public MediaPlayerException() {
        super();
    }

    public MediaPlayerException(String message) {
        super(message);
    }

    public MediaPlayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public MediaPlayerException(Throwable cause) {
        super(cause);
    }
}
