package world.domain.room;

import world.domain.Direction;

public enum RoomType {
    INDOOR, OUTDOOR;

    public static RoomType fromName(String text){
        RoomType[] values = values();
        for (RoomType value : values) {
            if (value.name().equalsIgnoreCase(text)) {
                return value;
            }
        }
        return null;
    }
}
