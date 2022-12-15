package character.outfit;

import world.domain.item.Item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static character.outfit.ItemTypeOutfitLocationMapper.outfitLocationForItemType;
import static java.util.Collections.emptyList;
import static org.apache.commons.collections.CollectionUtils.isEmpty;

/**
 * Contains the items that the player wears on his/her body.
 */
public class Outfit {
    private List<Item> wornItems;

    public Outfit() {
        wornItems = new ArrayList<>();
    }

    /**
     * Method to add an item to the outfit. False is returned when it was
     * not possible.
     */
    public boolean add(Item item){
        if (wornItems == null){
            wornItems = new ArrayList<>();
        }
        List<OutfitLocation> outfitLocations = outfitLocationForItemType(item.getType());

        //return false if the item is not wearable
        if (isEmpty(outfitLocations)){
            return false;
        }

        //return false when location is already occupied
        List<OutfitLocation> occupied = occupiedLocations();
        for (OutfitLocation outfitLocation : outfitLocations) {
            if (occupied.contains(outfitLocation)){
                return false;
            }
        }

        return wornItems.add(item);
    }

    public List<OutfitLocation> occupiedLocations(){
        if (isEmpty(wornItems)){
            return emptyList();
        }
        List<OutfitLocation> occupiedLocations = new ArrayList<>();
        for (Item wornItem : wornItems) {
            List<OutfitLocation> locationsOfItem = outfitLocationForItemType(wornItem.getType());
            occupiedLocations.addAll(locationsOfItem);
        }
        return occupiedLocations;
    }

    public boolean remove(Item item) {
        if (isEmpty(wornItems)){
            return false;
        }
        Iterator<Item> iterator = wornItems.iterator();
        while (iterator.hasNext()){
            Item wornItem = iterator.next();
            if (wornItem.equals(item)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
