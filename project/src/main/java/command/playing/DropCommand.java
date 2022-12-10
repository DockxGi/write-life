package command.playing;

import character.player.domain.Player;
import command.ArgumentCommand;
import game.GameModel;
import org.apache.commons.lang3.StringUtils;
import world.domain.item.Item;
import world.domain.room.Room;

import static utils.PrintLineUtil.printEvent;

public class DropCommand extends ArgumentCommand {

    @Override
    public String keyword() {
        return "drop";
    }

    @Override
    public void execute(String[] splitted, GameModel game) {
        Room currentRoom = game.getCurrentRoom();
        if (currentRoom == null){
            System.out.println("You need to be in a room to drop items in it.");
            return;
        }
        if (!hasArgument(splitted)){
            System.out.println("You have to specify what you want to drop. See: HELP DROP");
            return;
        }
        String argument = splitted[1];
        if (splitted.length < 3){
            dropItemByName(game, argument);
        } else if (splitted[1].equals("item")){
            String position = splitted[2];
            dropItemByPosition(game, position);
        }
    }

    private void dropItemByPosition(GameModel game, String position) {
        if (!StringUtils.isNumeric(position)){
            System.out.println("You have to specify a numeric position. See: HELP DROP (example: take item 1)");
            return;
        }
        Integer numericPosition = Integer.valueOf(position);
        Player player = game.getPlayer();
        Item item = player.getItemByPosition(numericPosition);

        if (item == null){
            System.out.printf("There is no item at position %s.%n", position);
            return;
        }
        dropItem(game, item);
    }

    private void dropItem(GameModel game, Item item) {
        Room currentRoom = game.getCurrentRoom();
        Player player = game.getPlayer();
        currentRoom.addItemTemporarily(item);
        player.removeItem(item);
        printEvent("ITEM REMOVED FROM INVENTORY");
        System.out.println("The items is temporarily added to the room. It can get out of sight at any moment.");
    }

    private void dropItemByName(GameModel game, String itemName) {
        Player player = game.getPlayer();
        Item item = player.getItemByName(itemName);
        if (item == null){
            System.out.printf("No item found with name %s.%n", itemName);
            return;
        }
        dropItem(game, item);
    }
}
