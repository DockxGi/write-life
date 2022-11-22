package game;

import player.domain.Player;
import world.domain.World;
import world.domain.WorldSession;
import world.persist.WorldJsonFileRepository;
import world.persist.WorldRepository;

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

    public void stopWorldSession() {
        this.worldSession = null;
    }

    public Player getPlayer() {
        if (worldSession == null){
            return null;
        }
        return worldSession.getPlayer();
    }

    public World getWorld() {
        if (worldSession == null){
            return null;
        }
        return worldSession.getWorld();
    }
}
