package exception;

public class LoadImageException extends RuntimeException {
    public LoadImageException(String message, Throwable cause) {
        super(message, cause);
    }
}
