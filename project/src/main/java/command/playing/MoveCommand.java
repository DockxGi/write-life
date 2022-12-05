package command.playing;

import command.ArgumentCommand;
import game.GameModel;
import world.domain.Direction;
import world.domain.room.Exit;
import world.domain.room.Room;

public class MoveCommand extends ArgumentCommand {

    @Override
    public String keyword() {
        return "move";
    }

    @Override
    public void execute(String[] splitted, GameModel game) {
        if (!game.hasWorldSession() || game.getCurrentRoom() == null){
            System.out.println("You can only move when you are in a room of a world.");
            return;
        }
        if (!hasArgument(splitted)){
            System.out.println("You have to specify in which direction you want to move. See: HELP MOVE");
            return;
        }
        String argument = splitted[1];
        Direction direction = Direction.fromNameOrAbbreviation(argument);
        if (direction == null){
            System.out.println(argument + " is not a valid direction. See: HELP MOVE");
            return;
        }
        Exit exit = game.getCurrentRoom().getExit(direction);
        if (exit == null){
            System.out.println("The room has no exit in that direction. Use look command to see the exits.");
            return;
        }
        if (exit.isLocked()){
            System.out.println("The exit is locked. You can not move trough it.");
            return;
        }
        String destinationRoomName = exit.getDestinationRoom();
        Room destination = game.getWorld().findRoom(destinationRoomName);
        if (destination == null){
            System.out.println("There is no room behind the exit.");
            return;
        }
        game.moveToRoom(destination);
        System.out.println("[MOVED TO OTHER ROOM]");
    }
}
