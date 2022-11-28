package world.domain.room;

import lombok.Getter;
import world.domain.Direction;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class Room {
    @Getter
    private String name; //lowercase name of the room, unique within the world
    @Getter
    private String description;
    @Getter
    private boolean ladingSpot; //determines if player can land directly into this room

    private Map<Direction, Exit> exits;

    public Room(String name, String description, boolean ladingSpot) {
        this.name = name.trim().toLowerCase(Locale.ROOT);
        this.description = description;
        this.ladingSpot = ladingSpot;

        exits = new HashMap<>();
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
}
