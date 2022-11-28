package command;

import game.GameModel;
import world.domain.room.Room;

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
            System.out.println("You are floating above the world.");
            System.out.println("Use HELP LAND to see how you can land somewhere.");
        }
    }
}
