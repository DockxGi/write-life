package character.player.domain;

import character.Inventory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import world.domain.cost.Price;
import world.domain.item.Item;
import world.domain.item.ItemType;
import world.domain.room.feature.Feature;
import world.domain.room.search.FeatureSearchChances;
import world.domain.room.search.SearchChance;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextInt;

@Getter
@Setter
@AllArgsConstructor
public class Player {

    private String name;
    private Inventory inventory;

    public Player(String name) {
        this.name = name;
        inventory = new Inventory();
    }

    /**
     * Player takes an item and puts it in the inventory.
     * False is returned if it is not possible to add it to the inventory.
     */
    public boolean takeItem(Item item){
        if (this.inventory == null){
            this.inventory = new Inventory();
        }
        return inventory.addItem(item);
    }

    public boolean canTakeItem(){
        if (inventory == null){
            inventory = new Inventory();
        }
        return !inventory.isFull();
    }

    //TODO: adapt so that if the inventory contains the items that it returns true
    public boolean canPay(Price price) {
        if (price == null){
            return true;
        }
        return false;
    }

    /**
     * Player searches around the feature and might find something.
     */
    public List<Item> searchAround(Feature feature) {
        List<SearchChance> chances = FeatureSearchChances.searchChancesFor(feature.getType());
        if (chances == null){
            return null;
        }
        List<Item> itemsFound = new ArrayList<>();
        for (SearchChance chance : chances) {
            Item item = tryToFindItem(feature, chance);
            if (item != null){
                itemsFound.add(item);
            }
        }
        return itemsFound;
    }

    private Item tryToFindItem(Feature feature, SearchChance chance) {
        int d100result = nextInt(1, 101);
        boolean itemFound = d100result <= chance.getChance();

        if (itemFound){
            return createFoundItem(feature, chance);
        }
        return null;
    }

    private Item createFoundItem(Feature feature, SearchChance chance) {
        ItemType itemType = chance.getItemType();
        //quality is equal to feature quality, if feature quality is null then it is random
        Integer quality = feature.getQuality() == null ? nextInt(1,101) : feature.getQuality();
        return new Item(itemType, quality);
    }
}