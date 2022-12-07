package command.playing;

import character.player.domain.Player;
import command.ArgumentCommand;
import command.describers.ItemDescriber;
import command.describers.LevelOfDetail;
import game.GameModel;
import org.apache.commons.lang3.StringUtils;
import world.domain.item.Item;
import world.domain.room.Room;
import world.domain.room.feature.Feature;

import java.util.List;

import static command.describers.LevelOfDetail.MEDIUM;

public class SearchCommand extends ArgumentCommand {
    @Override
    public String keyword() {
        return "search";
    }

    @Override
    public void execute(String[] splitted, GameModel game) {
        if (game.getCurrentRoom() == null){
            System.out.println("You have to be in a room to be able to search.");
            return;
        }
        if (!hasArgument(splitted)){
            System.out.println("You have to specify where you search. See: HELP SEARCH");
            return;
        }
        String argument = splitted[1];
        if (argument.equals("room")){
            handleSearchRoom(splitted, game.getCurrentRoom());
        } else if (argument.equals("feature")){
            handleSearchFeature(splitted, game.getCurrentRoom(), game.getPlayer());
        }
    }

    private void handleSearchFeature(String[] splitted, Room currentRoom, Player player) {
        if(splitted.length < 3){
            System.out.println("You have to specify the name of the feature around which you want to search. See: HELP SEARCH");
            return;
        }
        String featureName = splitted[2];
        String featureNameWithoutDoubleQuotes = StringUtils.replace(featureName, "_", " ");

        Feature feature = currentRoom.featureWithName(featureNameWithoutDoubleQuotes);
        if (feature == null){
            System.out.println("There is no feature with the name " + featureNameWithoutDoubleQuotes + " in the room.");
            return;
        }

        List<Item> items = player.searchAround(feature);
        showSearchResult(items);
        //todo: let the player choose what to take
    }

    private void showSearchResult(List<Item> items) {
        ItemDescriber itemDescriber = new ItemDescriber();
        String description = itemDescriber.describeList(items, MEDIUM);
        System.out.println(description);
    }

    private void handleSearchRoom(String[] splitted, Room currentRoom) {
        //TODO: adapt this once things can be hidden in the room
        System.out.println("You search the room but you find nothing.");
    }
}
