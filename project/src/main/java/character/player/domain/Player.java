package character.player.domain;

import character.Inventory;
import character.outfit.Outfit;
import character.outfit.OutfitLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import world.domain.cost.ItemQualityRequirement;
import world.domain.cost.Price;
import world.domain.item.Item;
import world.domain.item.ItemType;
import world.domain.room.feature.Feature;
import world.domain.room.search.FeatureSearchChances;
import world.domain.room.search.SearchChance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.apache.commons.collections.MapUtils.isEmpty;
import static org.apache.commons.lang3.RandomUtils.nextInt;

@Getter
@Setter
@AllArgsConstructor
public class Player {

    private String name;
    private Inventory inventory;
    private Outfit outfit;

    public Player(String name) {
        this.name = name;
        inventory = new Inventory();
        outfit = new Outfit();
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

    public boolean wearItem(Item item){
        if (outfit == null){
            outfit = new Outfit();
        }
        boolean added = outfit.add(item);
        if (added){
            inventory.removeItem(item);
        }
        return added;
    }

    public boolean canTakeItem(){
        if (inventory == null){
            inventory = new Inventory();
        }
        return !inventory.isFull();
    }

    public boolean canPay(Price price) {
        if (price == null || isEmpty(price.getItems())){
            return true;
        }
        Map<ItemQualityRequirement, Integer> priceItems = price.getItems();
        for (ItemQualityRequirement itemQualityRequirement : priceItems.keySet()) {
            int amountOfQualityItems = amountOfMatchingItems(itemQualityRequirement);
            int neededAmount = priceItems.get(itemQualityRequirement);
            if (amountOfQualityItems < neededAmount){
                return false;
            }
        }
        return true;
    }

    /**
     * Player pays the price. The method returns the entire list of qualities of these items, including null values.
     */
    public List<Integer> pay(Price price) {
        List<Integer> qualitiesOfItems = new ArrayList<>();
        if (price == null || isEmpty(price.getItems())){
            return qualitiesOfItems;
        }
        Map<ItemQualityRequirement, Integer> priceItems = price.getItems();
        for (ItemQualityRequirement itemQualityRequirement : priceItems.keySet()) {
            int requiredAmount = priceItems.get(itemQualityRequirement);
            List<Integer> newQualities = inventory.removeItems(itemQualityRequirement, requiredAmount);
            qualitiesOfItems.addAll(newQualities);
        }
        return qualitiesOfItems;
    }

    private int amountOfMatchingItems(ItemQualityRequirement itemQualityRequirement) {
        List<Item> inventoryItems = getInventoryItems();
        if (isEmpty(inventoryItems)){
            return 0;
        }

        int amount = 0;
        for (Item item : inventoryItems) {
            if (item.matches(itemQualityRequirement)){
                amount += 1;
            }
        }
        return amount;
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
            Item foundItem = createFoundItem(feature, chance);
            return foundItem;
        }
        return null;
    }

    private Item createFoundItem(Feature feature, SearchChance chance) {
        ItemType itemType = chance.getItemType();
        //quality is equal to feature quality, if feature quality is null then it is random
        Integer quality = feature.getQuality() == null ? nextInt(1,101) : feature.getQuality();
        return new Item(itemType, quality);
    }

    public List<Item> getInventoryItems() {
        if (inventory == null){
            return null;
        }
        return inventory.getItems();
    }

    public Item getItemByPosition(Integer numericPosition) {
        if (inventory == null || inventory.isEmpty()){
            return null;
        }
        return inventory.getItemByPosition(numericPosition);
    }

    public Item getItemByName(String argument) {
        return inventory.getItemByName(argument);
    }

    public void removeItem(Item item) {
        inventory.removeItem(item);
    }

    public boolean takeOffClothesAt(OutfitLocation location) {
        if (outfit == null){
            outfit = new Outfit();
            return false;
        }
        //the inventory must have place to put the item
        if (inventory.isFull()){
            return false;
        }
        Item item = outfit.removeItemAt(location);
        inventory.addItem(item);
        return item != null;
    }
}
