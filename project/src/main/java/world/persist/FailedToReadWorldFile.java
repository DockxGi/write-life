package world.persist;

public class FailedToReadWorldFile extends RuntimeException{
    public FailedToReadWorldFile(String message, Throwable cause) {
        super(message, cause);
    }
}
