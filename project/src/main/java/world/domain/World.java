package world.domain;

import world.domain.room.Room;

import java.util.HashSet;
import java.util.Set;

public class World {
    private String name;
    private Set<Room> rooms;

    public World(String name) {
        this.name = name;
        this.rooms = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public boolean hasRoom(String roomName) {
        if (rooms == null){
            return false;
        }
        for (Room room : rooms) {
            if (room.getName().equalsIgnoreCase(roomName)){
                return true;
            }
        };
        return false;
    }

    public void addRoom(Room room) {
        if (rooms == null){
            rooms = new HashSet<>();
        }
        rooms.add(room);
    }
}
