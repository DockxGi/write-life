package command;

import game.GameModel;

import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {

    private Map<String, Command> commands;
    private GameModel gameModel;

    public CommandProcessor() {
        this.commands = new HashMap<>();
        gameModel = new GameModel();

        supportCommand(new HelpCommand());
        supportCommand(new WorldCommand());
        supportCommand(new PlayerCommand());
        supportCommand(new LookCommand());
        supportCommand(new RoomCommand());
    }

    private void supportCommand(Command command){
        commands.put(command.keyword(), command);
    }

    public void processCommand(String nextLine) {
        String[] splitted = nextLine.split(" ");

        String commandKeyword = splitted[0];
        Command command = commands.get(commandKeyword);

        if (command != null){
            command.execute(splitted, gameModel);
        }
    }
}
