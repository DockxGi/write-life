package command;

import org.apache.commons.lang3.StringUtils;
import resources.ResourceReader;
import world.domain.World;
import world.view.WorldMenus;
import world.persist.WorldJsonFileRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class CommandProcessor {

    private WorldMenus worldMenus;
    private Map<String, Command> commands;

    public CommandProcessor(ResourceReader resourceReader) {
        this.worldMenus = new WorldMenus();
        this.commands = new HashMap<>();

        supportCommand(new HelpCommand());
        supportCommand(new WorldCommand());
    }

    private void supportCommand(Command command){
        commands.put(command.keyword(), command);
    }

    public void processCommand(String nextLine) {
        String[] splitted = nextLine.split(" ");

        String commandKeyword = splitted[0];
        Command command = commands.get(commandKeyword);

        if (command != null){
            command.execute(splitted);
        }
    }
}
