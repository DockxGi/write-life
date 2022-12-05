package command;

import game.GameModel;
import utils.PrintLineUtil;
import world.domain.room.Room;
import world.view.RoomMenus;

import java.util.ArrayList;
import java.util.List;

public class LandCommand extends ArgumentCommand {
    @Override
    public String keyword() {
        return "land";
    }

    @Override
    public void execute(String[] splitted, GameModel game) {
        boolean hasArgument = hasArgument(splitted);

        if (!hasArgument){
            processLandCommand(game);
            return;
        }

        String argument = splitted[1];
        if (argument.equals("list")){
            processLandListCommand(game);
        }
    }

    private void processLandListCommand(GameModel game) {
        if (!game.hasWorldSession()){
            System.out.println("You can only see the list of landing spots when you are in a world.");
            return;
        }

        List<Room> landingSpots = game.getWorld().getLandingSpots();
        if (landingSpots.isEmpty()){
            System.out.println("The world has no landing spots.");
            return;
        }

        List<String> roomNames = new ArrayList<>();
        for (Room landingSpot : landingSpots) {
            roomNames.add(landingSpot.getName());
        }
        PrintLineUtil.printAsOrderedList(roomNames);
    }

    private void processLandCommand(GameModel game) {
        if (!game.hasWorldSession()){
            System.out.println("You can only land when you are in a world.");
            return;
        }

        Room currentRoom = game.getCurrentRoom();
        if (currentRoom != null){
            System.out.println();
            System.out.println("You can only land when you hover above the world.");
            return;
        }

        RoomMenus roomMenus = new RoomMenus();
        Room room = roomMenus.showChooseRoomMenu(game, "In which room do you want to land? (empty to cancel)");
        if (room == null){
            return;
        }
        if (!room.isLadingSpot()){
            System.out.println("You can not land in the room " + room.getName() + " because it is not a landing spot.");
            return;
        }
        game.landInRoom(room);
    }
}
