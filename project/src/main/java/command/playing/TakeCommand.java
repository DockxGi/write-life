package command.playing;

import character.player.domain.Player;
import command.ArgumentCommand;
import game.GameModel;
import org.apache.commons.lang3.StringUtils;
import world.domain.item.Item;
import world.domain.room.Room;

import static utils.PrintLineUtil.printEvent;

public class TakeCommand extends ArgumentCommand {
    @Override
    public String keyword() {
        return "take";
    }

    @Override
    public void execute(String[] splitted, GameModel game) {
        Room currentRoom = game.getCurrentRoom();
        if (currentRoom == null){
            System.out.println("You need to be in a room to take items from it.");
            return;
        }
        if (!hasArgument(splitted)){
            System.out.println("You have to specify what you want to take. See: HELP TAKE");
            return;
        }
        String argument = splitted[1];
        if (splitted.length < 3){
            takeItemByName(game, argument);
        } else if (splitted[1].equals("item")){
            String position = splitted[2];
            takeItemByPosition(game, position);
        }
    }

    private void takeItemByPosition(GameModel game, String position) {
        if (!StringUtils.isNumeric(position)){
            System.out.println("You have to specify a numeric position. See: HELP TAKE (example: take item 1)");
            return;
        }
        Integer numericPosition = Integer.valueOf(position);
        Room currentRoom = game.getCurrentRoom();
        Item item = currentRoom.getItemByPosition(numericPosition);

        if (item == null){
            System.out.println(String.format("There is no item at position %d.", position));
            return;
        }
        takeItem(game, item);
    }

    private void takeItemByName(GameModel game, String argument) {
        Room currentRoom = game.getCurrentRoom();

        String itemName = StringUtils.replace(argument, "_", " ");
        Item item = currentRoom.getItemByName(itemName);

        if (item == null){
            System.out.println(String.format("No item found with name %s.", itemName));
            return;
        }
        takeItem(game, item);
    }

    private void takeItem(GameModel game, Item item) {
        Player player = game.getPlayer();
        if (! player.canTakeItem()){
            System.out.println("You can not take the item because your inventory is full.");
            return;
        }
        player.takeItem(item);
        game.getCurrentRoom().removeItem(item);
        printEvent("ITEM ADDED TO INVENTORY");
    }
}
