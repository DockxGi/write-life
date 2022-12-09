package world.domain;

import lombok.Getter;
import character.player.domain.Player;
import world.domain.events.WeatherChanges;
import world.domain.room.Room;
import world.domain.room.RoomType;
import world.domain.time.TimeCycle;

/**
 * Represents the game-session that a player has in the world that he/she entered.
 */
@Getter
public class WorldSession {

    private Player player;
    private Room currentRoom; //room where player is located
    private World world;
    private TimeCycle timeCycle;

    public WorldSession(Player player, World world) {
        this.player = player;
        this.world = world;
        currentRoom = null;
        timeCycle = new TimeCycle();

        world.randomWeatherType();
        addTimeCycleEvents();
    }

    private void addTimeCycleEvents() {
        WeatherChanges weatherChanges = new WeatherChanges(world);
        timeCycle.addEvent(weatherChanges);
    }

    public void landInRoom(Room room) {
        this.currentRoom = room;
    }

    /**
     * Move player to other room and trigger a timeCycle tick.
     */
    public void moveToRoom(Room room) {
        currentRoom = room;
        timeCycle.tick();
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public void changeRoomType(RoomType roomType) {
        currentRoom.changeType(roomType);
    }
}
