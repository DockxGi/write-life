package world.view;

import game.GameModel;
import world.domain.room.Exit;
import world.domain.room.Room;

/**
 * Facade for showing a menu of the room-command.
 */
public class RoomMenus {

    private NewRoomMenu newRoomMenu;
    private ChooseRoomMenu chooseRoomMenu;
    private AddExitMenu addExitMenu;

    public RoomMenus() {
        newRoomMenu = new NewRoomMenu();
        chooseRoomMenu = new ChooseRoomMenu();
        addExitMenu = new AddExitMenu();
    }

    public Room showNewRoomMenu(GameModel game) {
        return newRoomMenu.interactWithUser(game);
    }

    public Room showChooseRoomMenu(GameModel game, String question) {
        return chooseRoomMenu.interactWithUser(game, question);
    }

    public Exit showAddExitMenu(Room room, Room destination) {
        return addExitMenu.interactWithUser(room, destination);
    }
}
