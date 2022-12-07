package command.administration;

import command.ArgumentCommand;
import game.GameModel;
import character.player.domain.Player;
import world.domain.World;
import world.domain.cost.Price;
import world.domain.room.Exit;
import world.domain.room.Room;
import world.domain.room.RoomType;
import world.domain.room.feature.Feature;
import world.domain.room.feature.FeatureType;
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
        } else if (argument.equals("add-exit")){
            processAddExitCommand(game);
        } else if (argument.equals("type")){
            processRoomTypeCommand(splitted, game);
        } else if (argument.equals("add-feature")){
            processAddFeatureCommand(splitted, game);
        }
    }

    private void processAddFeatureCommand(String[] splitted, GameModel game) {
        if (splitted.length < 3){
            System.out.println("You have to specify which type of feature you want to add. See: HELP FEATURE");
            return;
        }
        String argument = splitted[2];
        FeatureType type = FeatureType.fromName(argument);
        if (type == null){
            System.out.println(argument + " is not a valid type of feature. See: HELP FEATURE");
            return;
        }
        Price price = type.getPrice();
        Player player = game.getPlayer();
        if (!player.canPay(price)){
            System.out.println("You can not pay the price of the feature. See: HELP FEATURE");
            return;
        }
        Room currentRoom = game.getCurrentRoom();
        if (!currentRoom.canFeatureTypeBeAdded(type)){
            System.out.println("You can not add this type of feature to the room.");
            return;
        }
        Feature feature = roomMenus.showAddFeatureMenu(game.getCurrentRoom(), type);
        if (feature == null){
            System.out.println("[FEATURE CREATION FAILED]");
        }
        currentRoom.addFeature(feature);
        WorldJsonFileRepository.getInstance().save(game.getWorld());
        showRoomChangedAndSaved();
    }

    private void processRoomTypeCommand(String[] splitted, GameModel game) {
        if (game.getCurrentRoom() == null){
            System.out.println("You must be in the room to change the type of the room.");
            return;
        }
        if (splitted.length < 3){
            System.out.println("You should specify the type of the room. See: HELP ROOM");
            return;
        }
        String argument = splitted[2];
        RoomType roomType = RoomType.fromName(argument);
        if (roomType == null){
            System.out.println(argument + " is not a valid room type. See: HELP ROOM");
        }
        game.changeRoomType(roomType);
        WorldJsonFileRepository.getInstance().save(game.getWorld());
        showRoomChangedAndSaved();
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
        if (destination == null){
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

    private void showRoomChangedAndSaved(){
        printEvent("ROOM CHANGED AND WORLD SAVED");
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
