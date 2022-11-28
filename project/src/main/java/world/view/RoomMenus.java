package world.view;

import game.GameModel;
import world.domain.room.Room;

/**
 * Facade for showing a menu of the room-command.
 */
public class RoomMenus {

    private NewRoomMenu newRoomMenu;

    public RoomMenus() {
        newRoomMenu = new NewRoomMenu();
    }

    public Room showNewRoomMenu(GameModel game) {
        return newRoomMenu.interactWithUser(game);
    }
}
