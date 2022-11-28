package command;

import game.GameModel;
import world.domain.World;
import world.domain.room.Exit;
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
        if (argument.equals("add-exit")){
            processAddExitCommand(game);
        }
    }

    private void processAddExitCommand(GameModel game) {
        World world = game.getWorld();
        if (world.amountOfRooms() < 2){
            System.out.println("The world does not have enough rooms to add an exit from one room to another room.");
        }

        //owner room
        Room room = roomMenus.showChooseRoomMenu(game, "In which room do you want to place the exit? (empty to cancel)");
        if (room == null){
            showCanceled();
            return;
        }

        //destination room
        Room destination = roomMenus.showChooseRoomMenu(game, "Which room is at the other side of the exit? (empty to cancel)");
        if (room == null){
            showCanceled();
            return;
        }

        Exit exit = roomMenus.showAddExitMenu(room, destination);
        room.addExit(exit, exit.getDirection());
        WorldJsonFileRepository.getInstance().save(world);
        showExitSaved();
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

    private void showExitSaved(){
        printEvent("NEW EXIT ADDED TO ROOM AND WORLD SAVED");
    }

    private void showCanceled(){
        printEvent("CANCELED");
    }
}
