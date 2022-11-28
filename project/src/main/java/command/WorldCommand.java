package command;

import game.GameModel;
import player.persist.PlayerJsonFileRepository;
import world.domain.World;
import world.domain.WorldSession;
import world.persist.WorldJsonFileRepository;
import world.view.WorldMenus;

import java.util.List;

import static utils.PrintLineUtil.printAsOrderedList;
import static utils.PrintLineUtil.printEvent;

public class WorldCommand extends ArgumentCommand {

    private final WorldMenus worldMenus;

    public WorldCommand() {
        worldMenus = new WorldMenus();
    }

    @Override
    public String keyword() {
        return "world";
    }

    @Override
    public void execute(String[] splitted, GameModel gameModel) {
        boolean noArgument = !hasArgument(splitted);
        if (noArgument) {
            System.out.println("The command " + splitted[0] + " needs an argument. For more info type: help world");
            return;
        }
        String argument = splitted[1];
        if (argument.equals("new")) {
            processNewWorldCommand();
        }
        if (argument.equals("list")) {
            processListWorldCommand();
        }
        if (argument.equals("enter")) {
            processEnterWorldCommand(gameModel);
        }
        if (argument.equals("leave")) {
            processLeaveWorldCommand(gameModel);
        }
    }

    private void processLeaveWorldCommand(GameModel gameModel) {
        if (!gameModel.hasWorldSession()) {
            System.out.println("You are not in a world. So you can not leave one.");
            return;
        }
        PlayerJsonFileRepository.getInstance().save(gameModel.getPlayer());
        showPlayerSaved();

        WorldJsonFileRepository.getInstance().save(gameModel.getWorld());
        showWorldSaved();

        gameModel.stopWorldSession();
        showWorldLeft();
    }

    private void processEnterWorldCommand(GameModel gameModel) {
        if (gameModel.hasWorldSession()) {
            System.out.println("Please leave the current world first.");
            return;
        }
        WorldSession session = worldMenus.showEnterWorldMenu();
        gameModel.startWorldSession(session);
        showWorldEntered();
    }

    private void processListWorldCommand() {
        List<String> worldNames = WorldJsonFileRepository.getInstance().getAllWorldNames();
        printAsOrderedList(worldNames);
    }

    private void processNewWorldCommand() {
        World world = worldMenus.showNewWorldMenu();
        WorldJsonFileRepository.getInstance().save(world);
        showWorldSaved();
    }

    private void showWorldSaved() {
        printEvent("WORLD SAVED");
    }

    private void showPlayerSaved() {
        printEvent("PLAYER SAVED");
    }

    private void showWorldLeft() {
        printEvent("WORLD LEFT");
    }

    private void showWorldEntered() {
        printEvent("WORLD ENTERED");
    }
}
