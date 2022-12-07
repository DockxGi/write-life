package command.playing;

import command.ArgumentCommand;
import command.describers.FeatureDescriber;
import command.describers.ItemDescriber;
import command.describers.LevelOfDetail;
import game.GameModel;
import world.domain.Direction;
import world.domain.item.Item;
import world.domain.room.Exit;
import world.domain.room.Room;
import world.domain.room.feature.Feature;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Comparator.comparingInt;
import static org.apache.commons.collections.CollectionUtils.isEmpty;
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
        showItems(room);
        showFeatures(room);
        showExits(room);
    }

    private void showItems(Room room) {
        List<Item> items = room.getItems();
        if(!isEmpty(items)){
            ItemDescriber itemDescriber = new ItemDescriber();
            String itemsDescription = itemDescriber.describeList(items, LevelOfDetail.MEDIUM);
            System.out.println("\n" + itemsDescription);
        }
    }

    private void showExits(Room room) {
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

    private void showFeatures(Room room) {
        List<Feature> features = room.getFeatures();
        if (!isEmpty(features)){
            FeatureDescriber featureDescriber = new FeatureDescriber();
            String featuresDescription = featureDescriber.describeList(features, LevelOfDetail.LOW);
            System.out.println("\n-> Features: " + featuresDescription);
        }
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
