package game;

import player.domain.Player;
import world.domain.room.Room;
import world.domain.World;
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

    public Room getCurrentRoom() {
        //todo: rooms are not implemented yet, once implemented we should return it
        return null;
    }
}
