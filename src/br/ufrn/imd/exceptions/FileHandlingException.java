package br.ufrn.imd.exceptions;

public class FileHandlingException extends Exception {
	
    private static final long serialVersionUID = 2L;

    public FileHandlingException() {
        super();
    }

    public FileHandlingException(String message) {
        super(message);
    }

    public FileHandlingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileHandlingException(Throwable cause) {
        super(cause);
    }
}
