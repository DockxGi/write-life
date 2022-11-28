package command;

import game.GameModel;
import world.domain.World;
import world.domain.room.Room;
import world.persist.WorldJsonFileRepository;
import world.view.RoomMenus;

import static utils.PrintLineUtil.printEvent;

public class RoomCommand extends ArgumentCommand {

    private RoomMenus roomMenus;

    public RoomCommand() {
        roomMenus = new RoomMenus();
    }

    @Override
    public String keyword() {
        return "room";
    }

    @Override
    public void execute(String[] splitted, GameModel game) {
        if (!game.hasWorldSession()){
            System.out.println("You must be in a world to use the ROOM command.");
            return;
        }
        if (!hasArgument(splitted)){
            System.out.println("The ROOM command needs an argument. See: HELP ROOM");
            return;
        }
        String argument = splitted[1];
        if (argument.equals("new")){
            processNewRoomCommand(game);
        }
    }

    private void processNewRoomCommand(GameModel game) {
        Room room = roomMenus.showNewRoomMenu(game);
        World world = game.getWorld();
        world.addRoom(room);

        WorldJsonFileRepository.getInstance().save(world);
        showRoomSaved();
    }

    private void showRoomSaved() {
        printEvent("NEW ROOM ADDED TO WORLD AND WORLD SAVED");
    }
}
