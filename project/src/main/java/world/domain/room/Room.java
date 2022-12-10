package world.domain.room;

import lombok.Getter;
import org.apache.commons.collections.MapUtils;
import world.domain.Direction;
import world.domain.item.Item;
import world.domain.room.feature.Feature;
import world.domain.room.feature.FeatureType;

import java.util.*;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

public class Room {
    @Getter
    private String name; //lowercase name of the room, unique within the world
    @Getter
    private String description;
    @Getter
    private boolean ladingSpot; //determines if player can land directly into this room
    @Getter
    private RoomType type; //type of room, is optional

    private Map<Direction, Exit> exits;

    private List<Feature> features;

    private transient List<Item> tempItems; //items that are temporarily in the room (they are not saved)

    public Room(String name, String description, boolean ladingSpot) {
        this.name = name.trim().toLowerCase(Locale.ROOT);
        this.description = description;
        this.ladingSpot = ladingSpot;

        exits = new HashMap<>();
        features = new ArrayList<>();
        tempItems = new ArrayList<>();
    }

    /**
     * Rooms are equal when the names are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void addExit(Exit exit, Direction direction) {
        this.exits.put(direction, exit);
    }

    public Map<Direction, Exit> getExits() {
        return exits;
    }

    public Exit getExit(Direction direction) {
        if (MapUtils.isEmpty(exits)){
            return null;
        }
        return this.exits.get(direction);
    }

    public void changeType(RoomType roomType) {
        this.type = roomType;
        removeNotSupportedFeatures();
    }

    /**
     * Removes the not supported features from the room.
     */
    private void removeNotSupportedFeatures() {
        if (!features.isEmpty()){
            Iterator<Feature> iterator = features.iterator();
            while (iterator.hasNext()){
                Feature feature = iterator.next();
                FeatureType type = feature.getType();
                RoomType requiredRoomType = type.getRoomType();

                if (requiredRoomType != null && !requiredRoomType.equals(this.type)){
                    iterator.remove();
                }
            }
        }
    }

    private int countFeaturesOfType(FeatureType type){
        if (isEmpty(features)){
            return 0;
        }

        int i = 0;
        for (Feature feature : features) {
            if (feature.getType().equals(type)){
                i++;
            }
        }
        return i;
    }

    private int countAllFeatures(){
        if (isEmpty(features)){
            return 0;
        }
        return features.size();
    }

    public boolean canFeatureTypeBeAdded(FeatureType type) {
        RoomType requiredRoomType = type.getRoomType();
        boolean roomTypeOk = requiredRoomType == null || requiredRoomType.equals(this.getType());

        int maxInRoom = type.getMaxInRoom();
        boolean maxInRoomOk = countFeaturesOfType(type) < maxInRoom;

        boolean capacityReached = countAllFeatures() >= 10;

        return !capacityReached && roomTypeOk && maxInRoomOk;
    }

    public boolean hasFeatureWithName(String featureName) {
        if (isEmpty(features)){
            return false;
        }
        return features.stream().anyMatch(feature -> feature.getName().equals(featureName));
    }

    public void addFeature(Feature feature) {
        if (features == null){
            features = new ArrayList<>();
        }
        this.features.add(feature);
    }

    public Feature featureWithName(String featureName) {
        if (isEmpty(features)){
            return null;
        }
        return features.stream()
                .filter(feature -> feature.getName().equals(featureName))
                .findFirst()
                .orElse(null);
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public List<Item> getItems(){
        return tempItems;
    }

    public void replaceTempItems(List<Item> items) {
        this.tempItems = items;
    }

    public Item getItemByName(String itemName) {
        if (isEmpty(tempItems)){
            return null;
        }
        return tempItems.stream()
                .filter(item -> item.getName().equals(itemName))
                .findFirst()
                .orElse(null);
    }

    public Item getItemByPosition(Integer position) {
        if (isEmpty(tempItems)){
            return null;
        }
        if (tempItems.size() < position){
            return null;
        }
        return tempItems.get(position - 1);
    }

    public boolean hasFeatureOfType(FeatureType type) {
        if (isEmpty(features)){
            return false;
        }
        return features.stream()
                .anyMatch(feature -> feature.getType().equals(type));
    }

    public void replaceAllFeaturesOfType(FeatureType toReplace, FeatureType replacement) {
        if (this.features == null){
            return;
        }
        for (Feature feature : features) {
            if (toReplace.equals(feature.getType())){
                feature.setType(replacement);
            }
        }
    }

    public void removeItem(Item toRemove) {
        if (tempItems == null){
            return;
        }
        Iterator<Item> iterator = tempItems.iterator();
        while (iterator.hasNext()){
            Item item = iterator.next();
            if (toRemove.equals(item)){
                iterator.remove();
                return;
            }
        }
    }

    public void addItemTemporarily(Item item) {
        if (tempItems == null){
            tempItems = new ArrayList<>();
        }
        tempItems.add(item);
    }
}
