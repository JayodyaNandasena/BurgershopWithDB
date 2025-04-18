package exception;

public class QueryFailException extends RuntimeException {
    public QueryFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
