package command;

import world.domain.World;
import world.persist.WorldJsonFileRepository;
import world.view.WorldMenus;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class WorldCommand extends ArgumentCommand {

    private WorldMenus worldMenus;

    public WorldCommand() {
        worldMenus = new WorldMenus();
    }

    @Override
    public String keyword() {
        return "world";
    }

    @Override
    public void execute(String[] splitted) {
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
    }

    private void processListWorldCommand() {
        List<String> worldNames = WorldJsonFileRepository.getInstance().getAllWorldNames();

        int nr = 1;
        for (String worldName : worldNames) {
            System.out.println(String.format("%d. %s",nr, worldName));
            nr++;
        }
    }

    private void processNewWorldCommand() {
        World world = worldMenus.showNewWorldMenu();
        WorldJsonFileRepository.getInstance().save(world);
        showWorldSaved();
    }

    private void showWorldSaved(){
        System.out.println("[WORLD SAVED]");
    }
}
