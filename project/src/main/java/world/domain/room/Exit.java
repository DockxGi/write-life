package world.domain.room;

import lombok.Getter;
import world.domain.Direction;

/**
 * An exit in a room. It leads to another room.
 * It describes only one direction of a passage between two rooms.
 */
public class Exit {
    @Getter
    private String type; //optional type of exit
    @Getter
    private String description; //optional description
    @Getter
    private transient String parentRoom; //name of the room that owns the exit
    @Getter
    private transient Direction direction; //direction in the parentRoom where it is located
    @Getter
    private String destinationRoom; //name of the room where the exit leads to
    @Getter
    private boolean seeTrough; //if you can look into the destinationRoom
    @Getter
    private boolean locked; //if the exit is locked

    public Exit(String type, String description, String parentRoom, Direction direction, String destinationRoom, boolean seeTrough, boolean locked) {
        this.type = type;
        this.description = description;
        this.parentRoom = parentRoom;
        this.destinationRoom = destinationRoom;
        this.direction = direction;
        this.seeTrough = seeTrough;
        this.locked = locked;
    }
}
