package command;

import game.GameModel;
import world.domain.Direction;
import world.domain.room.Exit;
import world.domain.room.Room;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;

import static java.util.Comparator.comparingInt;
import static org.apache.commons.collections.MapUtils.isEmpty;
import static utils.PrintLineUtil.printAsTitle;

public class LookCommand extends ArgumentCommand {
    @Override
    public String keyword() {
        return "look";
    }

    @Override
    public void execute(String[] splitted, GameModel game) {
        if (!game.hasWorldSession()){
            System.out.println("You can only look when you are in a world.");
            return;
        }
        if (!hasArgument(splitted)){
            processLookInCurrentRoomCommand(game);
        }
    }

    private void processLookInCurrentRoomCommand(GameModel game) {
        Room room = game.getCurrentRoom();
        if (room == null){
            lookAboveWorld();
        } else {
            lookInRoom(room);
        }
    }

    private void lookInRoom(Room room) {
        printAsTitle(room.getName(), '-');

        String description = room.getDescription();
        if (description != null){
            System.out.println(description);
        }

        Map<Direction, Exit> exits = room.getExits();
        if (isEmpty(exits)){
            System.out.println("\n-> Exits: none");
            return;
        }
        System.out.println("\n-> Exits:");
        Set<Direction> directions = exits.keySet();
        directions.stream()
                .sorted(comparingInt(Direction::getOrder))
                .forEach(direction -> System.out.println(direction.name() + " " + shortExitDescription(exits.get(direction))));
    }

    private String shortExitDescription(Exit exit) {
        StringBuilder sb = new StringBuilder();

        String type = exit.getType();
        if (type != null){
            sb.append("(" + type + ") ");
        }
        if (exit.isLocked()){
            sb.append("[locked]");
        }
        if (!exit.isSeeTrough()){
            sb.append("[blocks vision]");
        }
        return  sb.toString();
    }

    private void lookAboveWorld() {
        System.out.println("You are floating above the world.");
        System.out.println("Use HELP LAND to see how you can land somewhere.");
    }
}
