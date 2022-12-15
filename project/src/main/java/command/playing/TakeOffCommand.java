package command.playing;

import character.outfit.OutfitLocation;
import character.player.domain.Player;
import command.ArgumentCommand;
import game.GameModel;
import world.domain.room.Room;

import static utils.PrintLineUtil.printEvent;

public class TakeOffCommand extends ArgumentCommand {
    @Override
    public String keyword() {
        return "take-off";
    }

    @Override
    public void execute(String[] splitted, GameModel game) {
        Room currentRoom = game.getCurrentRoom();
        if (currentRoom == null){
            System.out.println("You need to be in a room to change your outfit.");
            return;
        }
        if (!hasArgument(splitted)){
            System.out.println("You have to specify what you want to take off.");
            System.out.println();
            return;
        }
        String argument = splitted[1];
        if (splitted.length < 3){
            takeOffItemByName(game, argument);
        } else if (splitted[1].equals("item")){
            String position = splitted[2];
            takeOffItemByPosition(game, position);
        }
    }

    private void takeOffItemByPosition(GameModel game, String position) {
        OutfitLocation location = OutfitLocation.fromName(position);
        if (location == null){
            System.out.println(position + " is not a valid position.");
            return;
        }
        Player player = game.getPlayer();
        boolean success = player.takeOffClothesAt(location);
        if (success){
            printEvent("ITEM REMOVED FROM OUTFIT");
        } else {
            printEvent("FAILED TO REMOVE ITEM FROM OUTFIT");
            System.out.println("Check if there is an item at that position. See: HELP OUTFIT");
        }
    }

    private void takeOffItemByName(GameModel game, String argument) {
        //todo implement take off item by name
    }
}
