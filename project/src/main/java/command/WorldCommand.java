package command;

import game.GameModel;
import world.domain.World;
import world.domain.WorldSession;
import world.persist.WorldJsonFileRepository;
import world.view.WorldMenus;

import java.util.List;

import static utils.PrintLineUtil.printAsOrderedList;

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
        boolean noArgument = hasArgument(splitted);
        if (noArgument){
            System.out.println("The command " + splitted[0] + " needs an argument. For more info type: help world");
            return;
        }
        String argument = splitted[1];
        if (argument.equals("new")){
            processNewWorldCommand();
        }
        if (argument.equals("list")){
            processListWorldCommand();
        }
        if (argument.equals("enter")){
            processEnterWorldCommand(gameModel);
        }
    }

    private void processEnterWorldCommand(GameModel gameModel) {
        if (gameModel.hasWorldSession()){
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

    private void showWorldSaved(){
        System.out.println("[WORLD SAVED]");
    }

    private void showWorldEntered(){
        System.out.println(String.format("[WORLD ENTERED]"));
    }
}
