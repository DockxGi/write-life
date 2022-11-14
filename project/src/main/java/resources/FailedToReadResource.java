package resources;

public class FailedToReadResource extends RuntimeException {
    public FailedToReadResource(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToReadResource(String message) {
        super(message);
    }
}
