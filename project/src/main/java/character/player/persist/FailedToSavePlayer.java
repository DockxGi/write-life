package character.player.persist;

public class FailedToSavePlayer extends RuntimeException{
    public FailedToSavePlayer(String message) {
        super(message);
    }

    public FailedToSavePlayer(String message, Throwable cause) {
        super(message, cause);
    }
}
