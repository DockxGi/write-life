package game;

import world.domain.WorldSession;

/**
 * Represents the state of the current game.
 */
public class GameModel {

    private WorldSession worldSession;

    public boolean hasWorldSession() {
        return worldSession != null;
    }

    public void startWorldSession(WorldSession session) {
        if (hasWorldSession()){
            throw new FailedToStartWorldSession();
        }
        this.worldSession = session;
    }
}
