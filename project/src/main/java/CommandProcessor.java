import org.apache.commons.lang3.StringUtils;
import resources.ResourceReader;
import world.domain.World;
import world.view.WorldMenus;
import world.persist.WorldJsonFileRepository;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class CommandProcessor {

    private ResourceReader resourceReader;
    private WorldMenus worldMenus;

    public CommandProcessor(ResourceReader resourceReader) {
        this.resourceReader = resourceReader;
        this.worldMenus = new WorldMenus();
    }

    public void processCommand(String nextLine) {
        String[] splitted = nextLine.split(" ");

        if (StringUtils.equals(splitted[0], "help")){
            showHelp(splitted);
            return;
        }

        if (StringUtils.equals(splitted[0], "world")){
            processWorldCommand(splitted);
        }
    }

    private void processWorldCommand(String[] splitted) {
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

    private void showHelp(String[] splitted) {
        boolean noArgument = hasArgument(splitted);
        if (noArgument){
            System.out.println(resourceReader.readTextFile("help.txt"));
            return;
        }
        String output = resourceReader.readTextFile("help-" + splitted[1] + ".txt");
        System.out.println(output);
    }

    private boolean hasArgument(String[] splitted){
        return splitted.length < 2 || isBlank(splitted[1]);
    }
}
