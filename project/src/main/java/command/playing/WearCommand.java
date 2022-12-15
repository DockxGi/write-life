package command.playing;

import character.player.domain.Player;
import command.ArgumentCommand;
import game.GameModel;
import org.apache.commons.lang3.StringUtils;
import world.domain.item.Item;
import world.domain.room.Room;

import static utils.PrintLineUtil.printEvent;

public class WearCommand extends ArgumentCommand {
    @Override
    public String keyword() {
        return "wear";
    }

    @Override
    public void execute(String[] splitted, GameModel game) {
        Room currentRoom = game.getCurrentRoom();
        if (currentRoom == null){
            System.out.println("You need to be in a room to change your outfit");
            return;
        }
        if (!hasArgument(splitted)){
            System.out.println("You have to specify what you want to wear. See: HELP WEAR");
            return;
        }
        String argument = splitted[1];
        if (splitted.length < 3){
            wearItemByName(game, argument);
        } else if (splitted[1].equals("item")){
            String position = splitted[2];
            wearItemByPosition(game, position);
        }
    }

    private void wearItemByPosition(GameModel game, String position) {
        if (!StringUtils.isNumeric(position)){
            System.out.println("You have to specify a numeric position. See: HELP WEAR (example: wear item 1)");
            return;
        }
        Integer numericPosition = Integer.valueOf(position);
        Player player = game.getPlayer();
        Item item = player.getItemByPosition(numericPosition);

        if (item == null){
            System.out.printf("There is no item at position %s.%n", position);
            return;
        }
        wearItem(game, item);
    }

    private void wearItemByName(GameModel game, String itemName) {
        Player player = game.getPlayer();
        Item item = player.getItemByName(itemName);
        if (item == null){
            System.out.printf("No item found with name %s.%n", itemName);
            return;
        }
        wearItem(game, item);
    }

    private void wearItem(GameModel game, Item item) {
        Player player = game.getPlayer();
        boolean success = player.wearItem(item);
        if (! success){
            printEvent("FAILED TO WEAR ITEM");
            System.out.println("Make sure that the item is wearable.");
            System.out.println("Make sure that the location where the item should be added is not occupied already.");
        } else {
            printEvent("ITEM ADDED TO OUTFIT");
        }
    }
}
