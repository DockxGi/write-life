package character.player.persist;

public class FailedToReadPlayerFile extends RuntimeException{
    public FailedToReadPlayerFile(String message, Throwable cause) {
        super(message, cause);
    }
}
