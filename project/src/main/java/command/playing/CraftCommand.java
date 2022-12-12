package command.playing;

import character.player.domain.Player;
import command.ArgumentCommand;
import game.GameModel;
import utils.StatisticsUtil;
import world.domain.Theme;
import world.domain.cost.Price;
import world.domain.item.Item;
import world.domain.item.ItemType;
import world.domain.room.Room;
import world.domain.room.crafting.CraftBook;
import world.domain.room.crafting.CraftingLibrary;
import world.domain.room.crafting.Recipe;
import world.domain.room.feature.FeatureType;

import java.util.ArrayList;
import java.util.List;

public class CraftCommand extends ArgumentCommand {
    @Override
    public String keyword() {
        return "craft";
    }

    @Override
    public void execute(String[] splitted, GameModel game) {
        if (splitted.length < 3){
            System.out.println("To craft something you have to indicate the theme and item. See: HELP CRAFT");
            return;
        }
        CraftBook craftBook = takeCraftBookFromLibrary(splitted);
        ItemType itemType = ItemType.fromName(splitted[2]);
        if (itemType == null){
            System.out.println("Item " + splitted[2] + " does not exist.");
            return;
        }
        Recipe recipe = craftBook.findRecipeForItemType(itemType);
        if (recipe == null){
            System.out.println("The craft-book for theme " + splitted[1] + " contains no recipe for " + splitted[2] + ".");
            return;
        }
        Player player = game.getPlayer();
        Room currentRoom = game.getCurrentRoom();
        if (player == null || currentRoom == null){
            System.out.println("You need to be in a room to be able to craft.");
            return;
        }
        if (!player.canPay(recipe.getCreationCost())){
            System.out.println("You do not have the right ingredients to craft " + splitted[2] + ".");
            return;
        }
        if (!currentRoom.hasFeatureTypes(recipe.getRequiredFeatures())){
            System.out.println("The room has not the right features to craft " + splitted[2] + ".");
            return;
        }
        craft(recipe, game);
    }

    private void craft(Recipe recipe, GameModel game) {
        Player player = game.getPlayer();
        Room room = game.getCurrentRoom();
        Price price = recipe.getCreationCost();
        List<FeatureType> requiredFeatures = recipe.getRequiredFeatures();

        List<Integer> qualities = player.pay(price);
        List<Integer> featureQualities = room.onFeaturesUsed(requiredFeatures);
        qualities.addAll(featureQualities);

        int qualityOfResult = StatisticsUtil.average(qualities, 100);
        List<ItemType> result = recipe.getResult();
        List<Item> craftedItems = createCraftedItems(result, qualityOfResult);

        for (Item craftedItem : craftedItems) {
            room.addItemTemporarily(craftedItem);
        }
        System.out.println("The crafted items are temporarily added to the room. Take what you need before you lose it out of sight.");
    }

    private List<Item> createCraftedItems(List<ItemType> result, int qualityOfResult) {
        List<Item> craftedItems = new ArrayList<>();
        for (ItemType itemType : result) {
            Item craftedItem = new Item(itemType, qualityOfResult);
            craftedItems.add(craftedItem);
        }
        return craftedItems;
    }

    private CraftBook takeCraftBookFromLibrary(String[] splitted) {
        CraftingLibrary library = new CraftingLibrary();
        String theme = splitted[1].equals("none") ? null : splitted[1];
        CraftBook craftBook = library.findCraftBookByTheme(Theme.fromName(theme));
        return craftBook;
    }
}
