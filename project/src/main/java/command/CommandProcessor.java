package command;

import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {

    private Map<String, Command> commands;

    public CommandProcessor() {
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
