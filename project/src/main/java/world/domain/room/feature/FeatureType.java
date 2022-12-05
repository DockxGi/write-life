package world.domain.room.feature;

import lombok.Getter;
import world.domain.cost.Price;
import world.domain.cost.PriceBuilder;
import world.domain.room.RoomType;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static world.domain.item.ItemType.STONE;
import static world.domain.item.ItemType.WOOD;
import static world.domain.room.RoomType.OUTDOOR;

public enum FeatureType {
    ROCKS(1, OUTDOOR, null),
    WEATHER(1, OUTDOOR, null),
    CAMPFIRE(1,OUTDOOR, new PriceBuilder()
            .item(STONE,1,5)
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
}
