package world.domain;

import org.apache.commons.lang3.RandomUtils;
import world.domain.room.Room;
import world.domain.weather.WeatherType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static world.domain.weather.WeatherType.SUNNY;

public class World {
    private String name;
    private Set<Room> rooms;
    private transient WeatherType weatherType; //weatherType is not saved and is the same for all rooms with weather feature

    public World(String name) {
        this.name = name;
        this.rooms = new HashSet<>();
        weatherType = SUNNY;
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

    public int amountOfRooms() {
        if (rooms == null){
            return 0;
        }
        return rooms.size();
    }

    public Room findRoom(String roomName) {
        if (rooms == null){
            return null;
        }
        for (Room room : rooms) {
            String name = room.getName();
            if (name.equalsIgnoreCase(roomName)){
                return room;
            }
        }
        return null;
    }

    public List<Room> getLandingSpots() {
        if (isEmpty(rooms)){
            return emptyList();
        }
        return rooms.stream()
                .filter(Room::isLadingSpot)
                .collect(Collectors.toList());
    }

    /**
     * Sets the weatherType to something random.
     */
    public void randomWeatherType() {
        WeatherType[] weatherTypes = WeatherType.values();
        this.weatherType = weatherTypes[nextInt(0,3)];
    }

    public WeatherType getWeatherType() {
        return weatherType;
    }
}
