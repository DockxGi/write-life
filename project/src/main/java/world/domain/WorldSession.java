package world.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import player.domain.Player;
import world.domain.room.Room;

/**
 * Represents the game-session that a player has in the world that he/she entered.
 */
@Getter
public class WorldSession {

    private Player player;
    private Room currentRoom; //room where player is located
    private World world;

    public WorldSession(Player player, World world) {
        this.player = player;
        this.world = world;
        currentRoom = null;
    }

    public void landInRoom(Room room) {
        this.currentRoom = room;
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }
}
