package command;

import command.administration.PlayerCommand;
import command.administration.RoomCommand;
import command.administration.WorldCommand;
import command.playing.*;
import game.GameModel;

import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {

    private Map<String, Command> commands;
    private GameModel gameModel;
    private String lastCommand = null;

    public CommandProcessor() {
        this.commands = new HashMap<>();
        gameModel = new GameModel();

        supportCommand(new HelpCommand());
        supportCommand(new WorldCommand());
        supportCommand(new PlayerCommand());
        supportCommand(new LookCommand());
        supportCommand(new RoomCommand());
        supportCommand(new LandCommand());
        supportCommand(new MoveCommand());
        supportCommand(new FeatureCommand());
        supportCommand(new SearchCommand());
        supportCommand(new TakeCommand());
        supportCommand(new InventoryCommand());
        supportCommand(new DropCommand());
        supportCommand(new CraftBookCommand());
        supportCommand(new CraftCommand());
        supportCommand(new WearCommand());
        supportCommand(new OutfitCommand());
        supportCommand(new TakeOffCommand());
    }

    private void supportCommand(Command command){
        commands.put(command.keyword(), command);
    }

    public void processCommand(String nextLine) {
        String[] splitted = nextLine.split(" ");

        String commandKeyword = splitted[0];

        //if commandKeyword is repeat then we reproduce the previous command
        if (commandKeyword.equals("repeat")){
            splitted = lastCommand.split(" ");
            commandKeyword = splitted[0];
        }

        Command command = commands.get(commandKeyword);

        if (command != null){
            command.execute(splitted, gameModel);
            //remember last command so that player can repeat it later
            lastCommand = nextLine;
        }
    }
}
