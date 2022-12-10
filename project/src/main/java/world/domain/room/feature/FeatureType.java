package world.domain.room.feature;

import lombok.Getter;
import world.domain.cost.Price;
import world.domain.cost.PriceBuilder;
import world.domain.item.ItemType;
import world.domain.room.RoomType;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static world.domain.item.ItemType.*;
import static world.domain.room.RoomType.OUTDOOR;

public enum FeatureType {
    ROCKS(1, OUTDOOR, null),
    MEADOW(1,OUTDOOR, new PriceBuilder()
            .item(SEEDS, 1,5)
            .build()),
    WEATHER(1, OUTDOOR, null),
    SAPLING(10, OUTDOOR, new PriceBuilder()
            .item(ItemType.SAPLING,30, 1)
            .build()), //grows to tree
    TREE(10, OUTDOOR, new PriceBuilder()
            .item(GOD_POWER, 1,1).build()),
    SAPLINGS(1, OUTDOOR, new PriceBuilder()
            .item(ItemType.SAPLING,20, 5)
            .build()), //grows to trees
    TREES(1, OUTDOOR, new PriceBuilder()
            .item(GOD_POWER,1, 2)
            .build()),
    CAMPFIRE(1,OUTDOOR, new PriceBuilder()
            .item(STONE,1,4)
            .item(WOOD,1,1)
            .build())
    ;

    FeatureType(int maxInRoom, RoomType roomType, Price price) {
        this.maxInRoom = maxInRoom;
        this.roomType = roomType;
        this.price = price;
    }

    @Getter
    private int maxInRoom; //maximum amount of this type of feature in a room

    @Getter
    private RoomType roomType; //roomType that it needs

    @Getter
    private Price price; //what it costs to add the feature to a room (can be null)

    public static FeatureType fromName(String text) {
        FeatureType[] values = values();
        for (FeatureType value : values) {
            if (value.name().equalsIgnoreCase(text)) {
                return value;
            }
        }
        return null;
    }

    public static List<FeatureType> getAllForRoomType(RoomType filter) {
        if (filter == null){
            return Arrays.asList(values());
        }
        return stream(values())
                .filter(featureType -> featureType.roomType == null || featureType.roomType.equals(filter))
                .collect(toList());
    }

    /**
     * If free parameter is true then this returns all free FeatureTypes.
     * Otherwise, this returns all non-free featureTypes.
     */
    public static List<FeatureType> getAllByFree(boolean free) {
        return stream(values())
                .filter(featureType -> featureType.isFree() == free)
                .collect(toList());
    }

    private boolean isFree() {
        return price == null;
    }
}
