package world.persist;

public class FailedToSaveWorld extends RuntimeException {

    public FailedToSaveWorld(String message) {
        super(message);
    }

    public FailedToSaveWorld(String message, Throwable cause) {
        super(message, cause);
    }
}
