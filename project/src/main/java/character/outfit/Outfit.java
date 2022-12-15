package character.outfit;

import world.domain.item.Item;
import world.domain.item.ItemType;

import java.util.*;

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

    /**
     * Returns a map with as key the location of the item and as value what item is placed there.
     * If nothing is place there then the value is null for that key.
     * Every location is present in the map.
     */
    public Map<OutfitLocation, Item> itemsByLocation() {
        Map<OutfitLocation, Item> map = new HashMap<>();
        OutfitLocation[] locations = OutfitLocation.values();
        for (OutfitLocation location : locations) {
            map.put(location, getItemAtLocation(location));
        }
        return map;
    }

    public Item getItemAtLocation(OutfitLocation location){
        if (isEmpty(wornItems)){
            return null;
        }
        return wornItems.stream()
                .filter(item -> {
                    ItemType itemType = item.getType();
                    List<OutfitLocation> locationsOfItem = outfitLocationForItemType(itemType);
                    return locationsOfItem.contains(location);
                })
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns an estimation (percentage) about how much the outfit covers the body.
     */
    public int coverage() {
        if (isEmpty(wornItems)){
            return 0;
        }
        int coverage = 0;
        Map<OutfitLocation, Item> itemsByLocation = itemsByLocation();
        for (OutfitLocation location : itemsByLocation.keySet()) {
            if (itemsByLocation.get(location) != null){
                coverage += location.getCoverageImpact();
            }
        }
        return coverage;
    }
}
