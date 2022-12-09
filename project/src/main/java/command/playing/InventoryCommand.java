package command.playing;

import character.player.domain.Player;
import command.Command;
import command.describers.ItemDescriber;
import command.describers.LevelOfDetail;
import game.GameModel;
import org.apache.commons.collections.CollectionUtils;
import world.domain.item.Item;

import java.util.List;

import static command.describers.LevelOfDetail.MEDIUM;
import static org.apache.commons.collections.CollectionUtils.isEmpty;

public class InventoryCommand implements Command {
    @Override
    public String keyword() {
        return "inventory";
    }

    @Override
    public void execute(String[] splitted, GameModel game) {
        Player player = game.getPlayer();
        if (player == null){
            System.out.println("You have to enter the world with a player to see the inventory.");
            return;
        }
        List<Item> items = player.getInventoryItems();
        if (isEmpty(items)){
            System.out.println("Your inventory is empty.");
            return;
        }
        ItemDescriber itemDescriber = new ItemDescriber();
        String itemsDescription = itemDescriber.describeList(items, MEDIUM);
        System.out.println("Your inventory contains:");
        System.out.println(itemsDescription);
    }
}
